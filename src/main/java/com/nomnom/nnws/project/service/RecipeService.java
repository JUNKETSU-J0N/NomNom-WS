package com.nomnom.nnws.project.service;

import com.nomnom.nnws.project.dto.RecipeRequest;
import com.nomnom.nnws.project.dto.RecipeResponse;

import java.util.List;
import java.util.UUID;

public interface RecipeService {
    RecipeResponse createRecipe(RecipeRequest request);
    List<RecipeResponse> getAllRecipes();
    RecipeResponse getRecipeById(Long id);
    void deleteRecipe(Long id);
    RecipeResponse updateRecipe(Long id, RecipeRequest request);
    List<RecipeResponse> searchByNameAndDescription(String name);
    List<RecipeResponse> getAllRecipesFilteredAndShuffled(UUID userId);


    List<RecipeResponse> checkMatch(UUID userId);
    List<RecipeResponse> getHardResetAllEvaluations(UUID userId);
    List<RecipeResponse> getSoftResetAllEvaluations(UUID userId);
}
