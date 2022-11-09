package com.pack.shopping.cart.api.serviceImplement;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.pack.shopping.cart.api.dto.AuthorizationDTO;
import com.pack.shopping.cart.api.entity.AuthorizationEntity;
import com.pack.shopping.cart.api.entity.OrderEntity;
import com.pack.shopping.cart.api.exception.GenericAlreadyExistsException;
import com.pack.shopping.cart.api.exception.ResourceNotFoundException;
import com.pack.shopping.cart.api.repository.AuthorizationRepository;
import com.pack.shopping.cart.api.repository.OrderRepository;
import com.pack.shopping.cart.api.service.PaymentService;
import com.pack.shopping.cart.api.util.StatusEnum;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final AuthorizationRepository authorizationRepository;

    private final OrderRepository orderRepository;

    public PaymentServiceImpl(AuthorizationRepository authorizationRepository, OrderRepository orderRepository) {
        this.authorizationRepository = authorizationRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<AuthorizationEntity> addauthorization(AuthorizationDTO authorize) {

        if (authorizationRepository.findByOrderId(UUID.fromString(authorize.getOrderId())) != null) {
            throw new GenericAlreadyExistsException("the order already has an authorization");
        }
        OrderEntity order = orderRepository.findById(UUID.fromString(authorize.getOrderId()))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (!Strings.isBlank(authorize.getError())) {
            order.setStatus(StatusEnum.valueOf("PAYMENT_FAILED"));
        } else if (authorize.getAuthorized()) {
            order.setStatus(StatusEnum.valueOf("PAID"));
        }
        return Optional.of(authorizationRepository.save(toEntity(authorize).setOrderEntity(order)));

    }

    @Override
    public AuthorizationEntity getByOrderId(String orderId) {
        return Optional.ofNullable(this.authorizationRepository.findByOrderId(UUID.fromString(orderId)))
                .orElseThrow(() -> new ResourceNotFoundException("order id - " + orderId));
    }

    private AuthorizationEntity toEntity(AuthorizationDTO auth) {
        AuthorizationEntity entity = new AuthorizationEntity();
        Optional<OrderEntity> order = orderRepository.findById(UUID.fromString(auth.getOrderId()));
        order.ifPresent(o -> entity.setOrderEntity(o));
        return entity.setAuthorized(auth.getAuthorized()).setTime(Timestamp.from(Instant.now())).setMessage(auth.getMessage())
                .setError(auth.getError());
    }
}
