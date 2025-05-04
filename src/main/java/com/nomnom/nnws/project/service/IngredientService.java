package com.nomnom.nnws.project.service;

import java.util.List;

import com.nomnom.nnws.project.dto.IngredientRequest;
import com.nomnom.nnws.project.dto.IngredientResponse;

public interface IngredientService {
    IngredientResponse createIngredient(IngredientRequest request);
    List<IngredientResponse> getAllIngredients();
    IngredientResponse getIngredientById(Long id);
    void deleteIngredient(Long id);
    IngredientResponse updateIngredient(Long id, IngredientRequest request);
}
