package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.PreferenceType;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {
    private String name;
    private String description;
    private PreferenceType preferenceType;
    private List<RecipeIngredientDto> ingredients;
}