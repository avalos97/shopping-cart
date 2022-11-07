package com.pack.shopping.cart.api.apiInterface;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import com.pack.shopping.cart.api.dto.CardDTO;

import io.swagger.annotations.*;

@Validated
@Api(value = "Card", description = "The Card API")
public interface CardApi {

    public static final String MEDIA_TYPE = "application/json";

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * 
     * @return a list of cards
     */
    @ApiOperation(value = "Returns all user's cards", nickname = "getAllCards", notes = "Returns all user's cards, else empty collection", response = CardDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful fetch.", response = CardDTO.class, responseContainer = "List") })
    @RequestMapping(value = "/api/cards", produces = { MEDIA_TYPE }, method = RequestMethod.GET)
    default ResponseEntity<List<CardDTO>> getAllCards() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"cardId\" : \"cardId\", \"cardNumber\" : \"cardNumber\", \"expires\" : \"expires\", \"cvv\" : \"cvv\", \"userId\" : \"userId\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 
     * @param ID of the requested card
     * @return a card
     */
    @ApiOperation(value = "Returns user's card", nickname = "getCardById", notes = "Returns user's card based on given card ID.", response = CardDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful fetch.", response = CardDTO.class) })
    @RequestMapping(value = "/api/cards/{id}", produces = { MEDIA_TYPE }, method = RequestMethod.GET)
    default ResponseEntity<CardDTO> getCardById(
            @ApiParam(value = "card Identifier", required = true) @PathVariable("id") String id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE,
                            "{  \"expires\" : \"expires\",  \"ccv\" : \"ccv\",  \"cardNumber\" : \"cardNumber\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * 
     * @param information of the new card to persist
     * @return the new card persisted
     */
    @ApiOperation(value = "Creates a new user card", nickname = "registerCard", notes = "Creates a new user addresses. Does nothing if address already exists.", response = CardDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "For successful fetch.", response = CardDTO.class) })
    @PostMapping(value = "/api/cards", produces = { MEDIA_TYPE }, consumes = { MEDIA_TYPE })
    default ResponseEntity<CardDTO> registerCard(
            @ApiParam(value = "card information") @Valid @RequestBody(required = false) CardDTO newCard) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(MEDIA_TYPE))) {
                    String exampleString = "{ \"cardId\" : \"cardId\", \"cardNumber\" : \"cardNumber\", \"expires\" : \"expires\", \"cvv\" : \"cvv\", \"userId\" : \"userId\" }";
                    ApiUtil.setExampleResponse(request, MEDIA_TYPE, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     *
     * @param id card Identifier (required)
     * @return Accepts the deletion request and perform deletion. If ID does not exist, does nothing. (status code 202)
     */
    @ApiOperation(value = "Deletes a card", nickname = "deleteCardById", notes = "Delete a card based on the given card ID.")
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Accepts the deletion request and perform deletion. If ID does not exist, does nothing.") })
    @DeleteMapping(
        value = "/api/cards/{id}"
    )
    default ResponseEntity<Void> deleteCardById(@ApiParam(value = "card Identifier",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
