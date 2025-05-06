package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.IngredientType;
import com.nomnom.nnws.project.enums.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRequest {
    private String name;
    private IngredientType type;
    private Unit unit;
}
