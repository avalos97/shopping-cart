package com.pack.shopping.cart.api.apiInterface;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.pack.shopping.cart.api.dto.ProductDTO;

import io.swagger.annotations.*;

@Validated
@Api(value = "Product", description = "the Product API")
public interface ProductApi {

    public static final String MEDIA_TYPE = "application/json";

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
    
    @ApiOperation(value = "Returns a product", nickname = "getProduct", notes = "Returns the product that matches the given product ID", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful fetch.", response = ProductDTO.class) })
    @GetMapping(value = "/products/{id}", produces = { MEDIA_TYPE })
    default ResponseEntity<ProductDTO> getProduct(
            @ApiParam(value = "Product Identifier", required = true) @PathVariable("id") String id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"price\" : \"\", \"imageUrl\" : \"imageUrl\", \"name\" : \"name\", \"count\" : 0, \"description\" : \"description\", \"productId\" : \"productId\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @ApiOperation(value = "Returns all the matched products", nickname = "queryProducts", notes = "Returns the products that matches the given query criteria", response = ProductDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful fetch.", response = ProductDTO.class, responseContainer = "List") })
    @GetMapping(value = "/products", produces = { MEDIA_TYPE })
    default ResponseEntity<List<ProductDTO>> queryProducts(
            @ApiParam(value = "Product name") @Valid @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Query page number", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(value = "Query page size", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"price\" : \"\", \"imageUrl\" : \"imageUrl\", \"name\" : \"name\", \"count\" : 0, \"description\" : \"description\", \"productId\" : \"productId\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
