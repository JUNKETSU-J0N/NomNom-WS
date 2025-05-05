package com.nomnom.nnws.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.entity.UserRecipeId;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, UserRecipeId> {

    @Modifying
    @Query("DELETE FROM UserRecipeId ri WHERE ri.recipe.id = :userRecipeId")
    void deleteAllByUserRecipeId(@Param("userRecipeId") Long userRecipeId);

    //TODO: brauchen iwr das? idk
    @Modifying
    @Query("SELECT FROM UserRecipe ur WHERE ur.user.id = :userId AND ur.recipe.id = :recipeId")
    UserRecipe findByUserIdAndRecipeId(@Param("userId") Long userId, @Param("recipeId") Long recipeId);

    @Modifying
    @Query("SELECT FROM UserRecipe ur WHERE ur.user.id = :userId")
    List<UserRecipe> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("SELECT FROM UserRecipe ur WHERE ur.recipe.id = :recipeId")
    List<UserRecipe> findAllByRecipeId(@Param("recipeId") Long recipeId);
}
