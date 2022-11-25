package com.pack.shopping.cart.api.security;

public class Constants {
    public static final String ENCODER_ID = "bcrypt";
    public static final String SIGNUP_URL = "/auth/user";
    public static final String TOKEN_URL = "/auth/token";
    public static final String REFRESH_URL = "/auth/token/refresh";
    public static final String SWAGGUER_UI = "/swagger-ui/**";
    public static final String SWAGGUER_RESOURCE = "/swagger-resources/**";
    public static final String API_DOCS = "/v2/api-docs";
    // public static final String WEB_JARS = "/webjars/**";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET_KEY = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 min
    public static final String ROLE_CLAIM = "roles";
    public static final String AUTHORITY_PREFIX = "ROLE_";
  }
