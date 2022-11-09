package com.pack.shopping.cart.api.service;

import java.util.List;

import com.pack.shopping.cart.api.dto.ItemDTO;
import com.pack.shopping.cart.api.entity.ItemEntity;

public interface ItemService {

    ItemEntity toEntity(ItemDTO m);

    List<ItemEntity> toEntityList(List<ItemDTO> items);

    ItemDTO toModel(ItemEntity e);

    List<ItemDTO> toModelList(List<ItemEntity> items);
}
