package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}

