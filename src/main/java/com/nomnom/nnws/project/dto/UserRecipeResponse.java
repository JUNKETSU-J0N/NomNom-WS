package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.entity.UserRecipeId;
import com.nomnom.nnws.project.enums.EvaluationValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecipeResponse {
    private UserRecipeId urId;
    private Long userId;
    private Long recipeId;
    private String notes;
    private EvaluationValue evaluation;
}
