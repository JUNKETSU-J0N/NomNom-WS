package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.enums.EvaluationValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import com.nomnom.nnws.project.entity.UserRecipeId;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, UserRecipeId> {
    List<UserRecipe> findByUserIdAndEvaluationIn(UUID userId, List<EvaluationValue> evaluationValues);
}
