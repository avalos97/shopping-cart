package com.pack.shopping.cart.api.apiInterface;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import com.pack.shopping.cart.api.dto.RefreshTokenDTO;
import com.pack.shopping.cart.api.dto.SignInDTO;
import com.pack.shopping.cart.api.dto.SignedInUserDTO;
import com.pack.shopping.cart.api.dto.UserDTO;

import io.swagger.annotations.*;

@Validated
@Api(value = "User", description = "the User API")
public interface AuthApi {

    public static final String MEDIA_TYPE = "application/json";

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Provides new JWT based on valid refresh token.", nickname = "getAccessToken", notes = "Provides new JWT based on valid refresh token.", response = SignedInUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful operation.", response = SignedInUserDTO.class) })
    @PostMapping(value = "/auth/token/refresh", produces = { MEDIA_TYPE }, consumes = { MEDIA_TYPE })
    default ResponseEntity<SignedInUserDTO> getAccessToken(
            @ApiParam(value = "Refresh token") @Valid @RequestBody(required = false) RefreshTokenDTO refreshToken) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"accessToken\" : \"accessToken\", \"userId\" : \"userId\", \"refreshToken\" : \"refreshToken\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Signin the customer (user)", nickname = "signIn", notes = "Signin the customer (user) that generates the JWT (access token) and refresh token, which can be used for accessing APIs.", response = SignedInUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For user sign-in. Once successful, user receives the access and refresh token.", response = SignedInUserDTO.class) })
    @PostMapping(value = "/auth/token", produces = { MEDIA_TYPE }, consumes = { MEDIA_TYPE })
    default ResponseEntity<SignedInUserDTO> signIn(
            @ApiParam(value = "User credentials") @Valid @RequestBody(required = false) SignInDTO signInReq) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"accessToken\" : \"accessToken\", \"userId\" : \"userId\", \"refreshToken\" : \"refreshToken\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Signouts the customer (user)", nickname = "signOut", notes = "Signouts the customer (user). It removes the refresh token from DB. Last issued JWT should be removed from client end that if not removed last for given expiration time.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepts the request for logout.") })
    @DeleteMapping(value = "/auth/token", consumes = { MEDIA_TYPE })
    default ResponseEntity<Void> signOut(
            @ApiParam(value = "Refresh token") @Valid @RequestBody(required = false) RefreshTokenDTO refreshToken) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Signup the a new customer (user)", nickname = "signUp", notes = "Creates a new customer (user), who can login and do the shopping.", response = SignedInUserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "For successful user creation.", response = SignedInUserDTO.class) })
    @PostMapping(value = "/users", produces = { MEDIA_TYPE }, consumes = { MEDIA_TYPE })
    default ResponseEntity<SignedInUserDTO> signUp(@ApiParam(value = "User Information") @Valid @RequestBody(required = false) UserDTO user) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"accessToken\" : \"accessToken\", \"userId\" : \"userId\", \"refreshToken\" : \"refreshToken\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
