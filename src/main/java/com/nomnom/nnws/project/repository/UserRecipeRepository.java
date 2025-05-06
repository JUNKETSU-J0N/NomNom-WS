package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.enums.EvaluationValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {
    List<UserRecipe> findByUserIdAndEvaluationIn(Long userId, List<EvaluationValue> evaluationValues);
}
