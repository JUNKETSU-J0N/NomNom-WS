package com.nomnom.nnws.project.service;

import com.nomnom.nnws.project.dto.RecipeRequest;
import com.nomnom.nnws.project.dto.RecipeResponse;

import java.util.List;

public interface RecipeService {
    RecipeResponse createRecipe(RecipeRequest request);
    List<RecipeResponse> getAllRecipes();
    RecipeResponse getRecipeById(Long id);
    void deleteRecipe(Long id);
    RecipeResponse updateRecipe(Long id, RecipeRequest request);
    List<RecipeResponse> searchByNameAndDescription(String name);
    List<RecipeResponse> getAllRecipesFilteredAndShuffled(Long userId);


    List<RecipeResponse> checkMatch(Long userId);
    List<RecipeResponse> getHardResetAllEvaluations(Long userId);
    List<RecipeResponse> getSoftResetAllEvaluations(Long userId);
}
