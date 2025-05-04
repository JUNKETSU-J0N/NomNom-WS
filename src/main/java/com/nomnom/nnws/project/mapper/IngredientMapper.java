package com.nomnom.nnws.project.mapper;

import org.springframework.stereotype.Component;

import com.nomnom.nnws.project.dto.IngredientRequest;
import com.nomnom.nnws.project.dto.IngredientResponse;
import com.nomnom.nnws.project.entity.Ingredient;

@Component
public class IngredientMapper {
    
    public IngredientResponse toResponse(Ingredient ingredient) {
        return new IngredientResponse(
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType(),
            ingredient.getUnit());
    }

    public Ingredient toEntity(IngredientRequest request) {
        return Ingredient.builder()
            .name(request.getName())
            .type(request.getType())
            .unit(request.getUnit())
            .build();
    }
}
