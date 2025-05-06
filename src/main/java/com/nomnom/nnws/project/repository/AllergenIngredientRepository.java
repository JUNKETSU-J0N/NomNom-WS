package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.AllergenIngredient;
import com.nomnom.nnws.project.entity.AllergenIngredientId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AllergenIngredientRepository extends JpaRepository<AllergenIngredient, AllergenIngredientId> {
    List<AllergenIngredient> findByAllergenIdIn(Set<Long> allergenIds);
}
