package com.pack.shopping.cart.api.serviceImplement;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import javax.transaction.Transactional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pack.shopping.cart.api.dto.RefreshTokenDTO;
import com.pack.shopping.cart.api.dto.SignedInUserDTO;
import com.pack.shopping.cart.api.dto.UserDTO;
import com.pack.shopping.cart.api.entity.CardEntity;
import com.pack.shopping.cart.api.entity.UserEntity;
import com.pack.shopping.cart.api.entity.UserTokenEntity;
import com.pack.shopping.cart.api.exception.GenericAlreadyExistsException;
import com.pack.shopping.cart.api.exception.InvalidRefreshTokenException;
import com.pack.shopping.cart.api.repository.UserRepository;
import com.pack.shopping.cart.api.repository.UserTokenRepository;
import com.pack.shopping.cart.api.security.JwtManager;
import com.pack.shopping.cart.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserTokenRepository userTokenRepository;
    private final JwtManager tokenManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserTokenRepository userTokenRepository, JwtManager tokenManager) {
        this.repository = repository;
        this.userTokenRepository = userTokenRepository;
        this.tokenManager = tokenManager;
    }

    @Override
    public void deleteCustomerById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Iterable<UserEntity> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardByCustomerId(String id) {
        AtomicReference<Optional<CardEntity>> cardEntity = new AtomicReference<>(Optional.empty());
        repository.findById(UUID.fromString(id))
                .ifPresent(ue -> {
                    if (Objects.nonNull(ue.getCard()) && !ue.getCard().isEmpty()) {
                        cardEntity.set(Optional.of(ue.getCard().get(0)));
                    }
                });
        return cardEntity.get();
    }

    @Override
    public Optional<UserEntity> getCustomerById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Override
    @Transactional
    public Optional<SignedInUserDTO> createUser(UserDTO user) {
        Integer count = repository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (count > 0) {
            throw new GenericAlreadyExistsException("Use different username and email.");
        }
        UserEntity userEntity = repository.save(toEntity(user));
        return Optional.of(createSignedUserWithRefreshToken(userEntity));
    }

    @Override
    @Transactional
    public SignedInUserDTO getSignedInUser(UserEntity userEntity) {
        userTokenRepository.deleteByUserId(userEntity.getUserId());
        return createSignedUserWithRefreshToken(userEntity);
    }

    private SignedInUserDTO createSignedUserWithRefreshToken(UserEntity userEntity) {
        return createSignedInUser(userEntity).refreshToken(createRefreshToken(userEntity));
    }

    private SignedInUserDTO createSignedInUser(UserEntity userEntity) {
        String token = tokenManager.create(org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Objects.nonNull(userEntity.getRole()) ? userEntity.getRole().name() : "")
                .build());
        return new SignedInUserDTO().username(userEntity.getUsername()).accessToken(token)
                .userId(userEntity.getUserId().toString());
    }

    @Override
    public Optional<SignedInUserDTO> getAccessToken(RefreshTokenDTO refreshToken) {
        // You may add an additional validation for time that would remove/invalidate
        // the refresh token
        return userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
                .map(ut -> Optional.of(createSignedInUser(ut.getUser()).refreshToken(refreshToken.getRefreshToken())))
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid token."));
    }

    @Override
    public void removeRefreshToken(RefreshTokenDTO refreshToken) {
        userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
                .ifPresentOrElse(userTokenRepository::delete, () -> {
                    throw new InvalidRefreshTokenException("Invalid token.");
                });
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        if (Strings.isBlank(username)) {
            throw new UsernameNotFoundException("Invalid user.");
        }
        final String uname = username.trim();
        Optional<UserEntity> oUserEntity = repository.findByUsername(uname);
        UserEntity userEntity = oUserEntity.orElseThrow(
                () -> new UsernameNotFoundException(String.format("Given user(%s) not found.", uname)));
        return userEntity;
    }

    private UserEntity toEntity(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntity;
    }

    private String createRefreshToken(UserEntity user) {
        String token = RandomHolder.randomKey(128);
        userTokenRepository.save(new UserTokenEntity().setRefreshToken(token).setUser(user));
        return token;
    }

    private static class RandomHolder {
        static final Random random = new SecureRandom();

        public static String randomKey(int length) {
            return String.format("%" + length + "s", new BigInteger(length * 5/* base 32,2^5 */, random)
                    .toString(32)).replace('\u0020', '0');
        }
    }
}
