package com.pack.shopping.cart.api.apiInterface;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.pack.shopping.cart.api.dto.AuthorizationDTO;
import com.pack.shopping.cart.api.dto.PaymentDetailDTO;

import io.swagger.annotations.*;

@Validated
@Api(value = "Payment", description = "the Payment API")
public interface PaymentApi {

    public static final String MEDIA_TYPE = "application/json";
    
    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Authorize a payment request", nickname = "authorize", notes = "Authorize a payment request.", response = AuthorizationDTO.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "For successful fetch.", response = AuthorizationDTO.class) })
    @PostMapping(
        value = "/payments",
        produces = { MEDIA_TYPE },
        consumes = { MEDIA_TYPE }
    )
    default ResponseEntity<AuthorizationDTO> authorize(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) PaymentDetailDTO paymentReq) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"orderId\" : \"orderId\", \"authorized\" : true, \"time\" : \"2000-01-23T04:56:07.000+00:00\", \"message\" : \"message\", \"error\" : \"error\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Returns the payment authorization", nickname = "getOrdersPaymentAuthorization", notes = "Return the payment authorization for the specified order", response = AuthorizationDTO.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "For successful fetch.", response = AuthorizationDTO.class) })
    @GetMapping(
        value = "/payments",
        produces = { MEDIA_TYPE }
    )
    default ResponseEntity<Authorization> getOrdersPaymentAuthorization(@NotNull @ApiParam(value = "Order Identifier", required = true) @Valid @RequestParam(value = "orderId", required = true) String orderId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"orderId\" : \"orderId\", \"authorized\" : true, \"time\" : \"2000-01-23T04:56:07.000+00:00\", \"message\" : \"message\", \"error\" : \"error\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
