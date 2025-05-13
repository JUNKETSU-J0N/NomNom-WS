package com.nomnom.nnws.project.controller;

import com.nomnom.nnws.project.dto.RecipeRequest;
import com.nomnom.nnws.project.dto.RecipeResponse;
import com.nomnom.nnws.project.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeResponse> create(@RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAll() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable Long id, @RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> searchByNameAndDescription(@RequestParam("searchTerm") String searchTerm) {
        return ResponseEntity.ok(recipeService.searchByNameAndDescription(searchTerm));
    }

    @GetMapping("/shuffled")
    public ResponseEntity<List<RecipeResponse>> getAllShuffled(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(recipeService.getAllRecipesFilteredAndShuffled(userId));
    }

    @GetMapping("/check-match")
    public ResponseEntity<List<RecipeResponse>> checkMatch(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(recipeService.checkMatch(userId));
    }


    @GetMapping("/{userId}/hard-reset-evaluations")
    public ResponseEntity<List<RecipeResponse>> getHardResetAllEvaluations(@PathVariable UUID userId) {
        return ResponseEntity.ok(recipeService.getHardResetAllEvaluations(userId));
    }

    @GetMapping("/{userId}/soft-reset-evaluations")
    public ResponseEntity<List<RecipeResponse>> getSoftResetAllEvaluations(@PathVariable UUID userId) {
        return ResponseEntity.ok(recipeService.getSoftResetAllEvaluations(userId));
    }

}

