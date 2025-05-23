package com.nomnom.nnws.project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nomnom.nnws.project.dto.IngredientResponse;
import com.nomnom.nnws.project.dto.ShoppingItemDto;
import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
import com.nomnom.nnws.project.entity.ShoppingItem;
import com.nomnom.nnws.project.entity.ShoppingList;
import com.nomnom.nnws.project.entity.User;

@Component
public class ShoppingListMapper {
    
    public ShoppingListResponse toResponse(ShoppingList shoppingList) {
        List<ShoppingItemDto> shoppingItemDtos = shoppingList.getItems().stream().map(si ->
                new ShoppingItemDto(
                    si.getId(),
                    si.getShoppingList().getId(),
                    new IngredientResponse(
                        si.getIngredient().getId(),
                        si.getIngredient().getName(),
                        si.getIngredient().getType(),
                        si.getIngredient().getUnit()),
                    si.getAmount(),
                    si.getUnit(),
                    si.isAdded()
            )
        ).collect(Collectors.toList());
        return new ShoppingListResponse(shoppingList.getId(), shoppingList.getUser().getId(), shoppingItemDtos);
    }

    public ShoppingList toEntity(ShoppingListRequest request, List<ShoppingItem> shoppingItems, User user) {
        return ShoppingList.builder()
            .items(shoppingItems)
            .user(user)
            .build();
    }
}
