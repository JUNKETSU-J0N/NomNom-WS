package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {}

