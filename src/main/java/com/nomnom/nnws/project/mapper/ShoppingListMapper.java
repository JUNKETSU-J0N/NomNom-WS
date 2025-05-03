package com.nomnom.nnws.project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nomnom.nnws.project.dto.IngredientDto;
import com.nomnom.nnws.project.dto.ShoppingItemDto;
//import com.nomnom.nnws.project.dto.ShoppingListRequestDto;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
//import com.nomnom.nnws.project.entity.ShoppingItem;
import com.nomnom.nnws.project.entity.ShoppingList;

@Component
public class ShoppingListMapper {
    
    public ShoppingListResponse toResponse(ShoppingList shoppingList) {
        List<ShoppingItemDto> shoppingItemDtos = shoppingList.getItems().stream().map(si ->
                new ShoppingItemDto(
                    si.getId(),
                    si.getShoppingList().getId(),
                    new IngredientDto(
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

    /* public ShoppingList toEntity(ShoppingListRequestDto request, List<ShoppingItem> shoppingItems) {
        return ShoppingList.builder()
            .items(shoppingItems)
            .user(null)
    } */
}
