package com.nomnom.nnws.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;
import com.nomnom.nnws.project.service.UserRecipeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userrecipe")
@RequiredArgsConstructor
public class UserRecipeController {
    
    private final UserRecipeService urService;

    @PostMapping
    public ResponseEntity<UserRecipeResponse> create(@RequestBody UserRecipeRequest request) {
        return ResponseEntity.ok(urService.createUserRecipe(request));
    }

    @GetMapping
    public ResponseEntity<List<UserRecipeResponse>> getAll() {
        return ResponseEntity.ok(urService.getAllUserRecipes());
    }

    @GetMapping("/{userId}/{recipeId}")
    public ResponseEntity<UserRecipeResponse> getById(@PathVariable Long userId, @PathVariable Long recipeId) {
        return ResponseEntity.ok(urService.getUserRecipeByUserIdAndRecipeId(userId, recipeId));
    }

//    @PutMapping("/{userId}/{recipeId}")
//    public ResponseEntity<UserRecipeResponse> updateUserRecipe(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody UserRecipeRequest request) {
//        return ResponseEntity.ok(urService.updateUserRecipe(userId, recipeId, request));
//    }

    @PutMapping("/{userId}/{recipeId}")
    public ResponseEntity<UserRecipeResponse> updateUserRecipe(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody UserRecipeRequest request) {
        return ResponseEntity.ok(urService.updateOrCreateUserRecipe(userId, recipeId, request));
    }

    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<UserRecipeResponse> deleteById(@PathVariable Long userId, @PathVariable Long recipeId) {
        urService.deleteUserRecipe(userId, recipeId);
        return ResponseEntity.noContent().build();
    }

}
