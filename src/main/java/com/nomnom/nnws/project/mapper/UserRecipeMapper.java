package com.nomnom.nnws.project.mapper;

import org.springframework.stereotype.Component;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;
import com.nomnom.nnws.project.entity.UserRecipe;

@Component
public class UserRecipeMapper {

    public UserRecipeResponse toResponse(UserRecipe recipe) {
        return new UserRecipeResponse(
            recipe.getUser().getId(),
            recipe.getRecipe().getId(),
            recipe.getNotes(),
            recipe.getEvaluation()
        );
    }
    
    public UserRecipe toEntity(UserRecipeRequest request) {
        return UserRecipe.builder()
            .user(null)
            .recipe(null)
            .notes(request.getNotes())
            .evaluation(request.getEvaluation())
            .build();
            //TODO: anders machen, das funktioniert so nicht?
    }
    
}
