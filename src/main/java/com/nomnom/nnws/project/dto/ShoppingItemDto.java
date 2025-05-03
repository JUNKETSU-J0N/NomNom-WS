package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItemDto {
    private Long id;
    private Long shoppingListId;
    private IngredientDto ingredient;
    private double amount;
    private Unit unit;
    private boolean addad;
}
