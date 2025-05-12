package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.Unit;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDto {
    private IngredientResponse ingredient;
    private double amount;
    private Unit unit;
}
