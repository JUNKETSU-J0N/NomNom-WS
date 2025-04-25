package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.RecipeIngredient;
import com.nomnom.nnws.project.entity.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId> {

    @Modifying
    @Query("DELETE FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId")
    void deleteAllByRecipeId(@Param("recipeId") Long recipeId);

}

