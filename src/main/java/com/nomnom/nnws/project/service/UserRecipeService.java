package com.nomnom.nnws.project.service;

import java.util.List;
import java.util.UUID;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;

public interface UserRecipeService {
    UserRecipeResponse createUserRecipe(UserRecipeRequest request);
    List<UserRecipeResponse> getAllUserRecipes();
    UserRecipeResponse getUserRecipeByUserIdAndRecipeId(UUID userId, Long recipeId);
    void deleteUserRecipe(UUID userId, Long recipeId);
    UserRecipeResponse updateUserRecipe(UUID userId, Long recipeId, UserRecipeRequest request);
    UserRecipeResponse updateOrCreateUserRecipe(UUID userId, Long recipeId, UserRecipeRequest newData);
}
