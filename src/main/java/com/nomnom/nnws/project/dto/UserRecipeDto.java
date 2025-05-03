package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.EvaluationValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecipeDto {
    private Long userId;
    private Long recipeId;
    private String notes;
    private EvaluationValue evaluation;
}
