package com.pack.shopping.cart.api.security;

import static com.pack.shopping.cart.api.security.Constants.AUTHORITY_PREFIX;
import static com.pack.shopping.cart.api.security.Constants.ROLE_CLAIM;

import com.pack.shopping.cart.api.serviceImplement.UserDetailServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter06 - Modern API Development with Spring and Spring Boot
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final Logger LOG = LoggerFactory.getLogger(getClass());
  private UserDetailServiceImpl userService;

  @Value("${app.security.jwt.keystore-location}")
  private String keyStorePath;
  @Value("${app.security.jwt.keystore-password}")
  private String keyStorePassword;
  @Value("${app.security.jwt.key-alias}")
  private String keyAlias;
  @Value("${app.security.jwt.private-key-passphrase}")
  private String privateKeyPassphrase;

  public SecurityConfig(UserDetailServiceImpl userService) {
    this.userService = userService;
  }

  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**",
      "/swagger-ui/**",
      "/v2/api-docs",
      "/webjars/**",
      "/auth/**"
  };

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(AUTH_WHITELIST);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable().formLogin().disable()
        .csrf()
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
        .cors()
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
            jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter authorityConverter = new JwtGrantedAuthoritiesConverter();
    authorityConverter.setAuthorityPrefix(AUTHORITY_PREFIX);
    authorityConverter.setAuthoritiesClaimName(ROLE_CLAIM);
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(authorityConverter);
    return converter;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    return userService;
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
    // configuration.setAllowCredentials(true);
    // For CORS response headers
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public KeyStore keyStore() {
    try {
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream(keyStorePath);
      keyStore.load(resourceAsStream, keyStorePassword.toCharArray());
      return keyStore;
    } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
      LOG.error("Unable to load keystore: {}", keyStorePath, e);
    }

    throw new IllegalArgumentException("Unable to load keystore");
  }

  @Bean
  public RSAPrivateKey jwtSigningKey(KeyStore keyStore) {
    try {
      Key key = keyStore.getKey(keyAlias, privateKeyPassphrase.toCharArray());
      if (key instanceof RSAPrivateKey) {
        return (RSAPrivateKey) key;
      }
    } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
      LOG.error("Unable to load private key from keystore: {}", keyStorePath, e);
    }
    throw new IllegalArgumentException("Unable to load private key");
  }

  @Bean
  public RSAPublicKey jwtValidationKey(KeyStore keyStore) {
    try {
      Certificate certificate = keyStore.getCertificate(keyAlias);
      PublicKey publicKey = certificate.getPublicKey();
      if (publicKey instanceof RSAPublicKey) {
        return (RSAPublicKey) publicKey;
      }
    } catch (KeyStoreException e) {
      LOG.error("Unable to load private key from keystore: {}", keyStorePath, e);
    }
    throw new IllegalArgumentException("Unable to load RSA public key");
  }

  @Bean
  public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
    return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
  }

}
