package com.nomnom.nnws.project.mapper;

import org.springframework.stereotype.Component;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;
import com.nomnom.nnws.project.entity.Recipe;
import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.entity.UserRecipeId;

@Component
public class UserRecipeMapper {

    public UserRecipeResponse toResponse(UserRecipe recipe) {
        UserRecipeId urId = new UserRecipeId(recipe.getUser().getId(), recipe.getRecipe().getId());
        return new UserRecipeResponse(
            urId,
            recipe.getUser().getId(),
            recipe.getRecipe().getId(),
            recipe.getNotes(),
            recipe.getEvaluation()
        );
    }
    
    public UserRecipe toEntity(UserRecipeRequest request, Recipe recipe, User user) {
        return UserRecipe.builder()
            .user(user)
            .recipe(recipe)
            .notes(request.getNotes())
            .evaluation(request.getEvaluation())
            .build();
    }
    
}
