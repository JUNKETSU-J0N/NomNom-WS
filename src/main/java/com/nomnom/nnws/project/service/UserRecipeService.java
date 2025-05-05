package com.nomnom.nnws.project.service;

import java.util.List;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;

public interface UserRecipeService {
    UserRecipeResponse createUserRecipe(UserRecipeRequest request);
    List<UserRecipeResponse> getAllUserRecipes();
    UserRecipeResponse getUserRecipeByUserIdAndRecipeId(Long userId, Long recipeId);
    void deleteUserRecipe(Long userId, Long recipeId);
    UserRecipeResponse updateUserRecipe(Long userId, Long recipeId, UserRecipeRequest request);
}
