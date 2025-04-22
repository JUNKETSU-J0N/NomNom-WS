package com.nomnom.nnws.project.mapper;

import com.nomnom.nnws.project.dto.RecipeIngredientDto;
import com.nomnom.nnws.project.dto.RecipeRequest;
import com.nomnom.nnws.project.dto.RecipeResponse;
import com.nomnom.nnws.project.entity.Recipe;
import com.nomnom.nnws.project.entity.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeResponse toResponse(Recipe recipe) {
        List<RecipeIngredientDto> ingredientDtos = recipe.getIngredients().stream().map(ri ->
                new RecipeIngredientDto(
                        ri.getIngredient().getId(),
                        ri.getAmount(),
                        ri.getUnit()
                )
        ).collect(Collectors.toList());

        return new RecipeResponse(recipe.getId(), recipe.getName(), recipe.getDescription(), ingredientDtos);
    }

    public Recipe toEntity(RecipeRequest request, List<RecipeIngredient> recipeIngredients) {
        return Recipe.builder()
                .name(request.getName())
                .description(request.getDescription())
                .ingredients(recipeIngredients)
                .build();
    }
}
