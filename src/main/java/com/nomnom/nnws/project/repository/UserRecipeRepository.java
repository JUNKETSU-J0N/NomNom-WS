package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.entity.UserRecipeId;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, UserRecipeId> {

    @Modifying
    @Query("DELETE FROM UserRecipeId ri WHERE ri.recipe.id = :userRecipeId")
    void deleteAllByuserRecipeId(@Param("userRecipeId") Long userRecipeId);

}
