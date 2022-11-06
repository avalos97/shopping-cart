package com.pack.shopping.cart.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Contains the new order request information")
public class NewOrderDTO extends RepresentationModel<NewOrderDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("card")
    private CardDTO card;

    @JsonProperty("items")
    @Valid
    private List<ItemDTO> items = null;

    
    /**
     * Get customerId
     * 
     * @return customerId
     */
    @ApiModelProperty(value = "Customer Identifier")
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public NewOrderDTO customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    
    /**
     * Get card
     * 
     * @return card
     */
    @ApiModelProperty(value = "Customer card")
    @Valid
    public CardDTO getCard() {
        return card;
    }
    
    public void setCard(CardDTO card) {
        this.card = card;
    }

    public NewOrderDTO card(CardDTO card) {
        this.card = card;
        return this;
    }

    
    /**
     * Items in shopping cart
     * 
     * @return items
     */
    @ApiModelProperty(value = "Items in shopping cart")
    @Valid
    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
    
    public NewOrderDTO items(List<ItemDTO> items) {
        this.items = items;
        return this;
    }

    public NewOrderDTO addItemsItem(ItemDTO itemsItem) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(itemsItem);
        return this;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewOrderDTO newOrder = (NewOrderDTO) o;
        return Objects.equals(this.customerId, newOrder.customerId) &&
                Objects.equals(this.card, newOrder.card) &&
                Objects.equals(this.items, newOrder.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, card, items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewOrder {\n");
        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        sb.append("    card: ").append(toIndentedString(card)).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
