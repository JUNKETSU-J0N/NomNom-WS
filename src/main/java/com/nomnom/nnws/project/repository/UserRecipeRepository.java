package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.entity.UserRecipeId;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, UserRecipeId> {

}
