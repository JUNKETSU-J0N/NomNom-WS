package com.nomnom.nnws.project.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {
    private String name;
    private String description;
    private List<RecipeIngredientDto> ingredients;
}