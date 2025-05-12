package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.enums.EvaluationValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.nomnom.nnws.project.entity.UserRecipeId;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, UserRecipeId> {
    List<UserRecipe> findByUserIdAndEvaluationIn(Long userId, List<EvaluationValue> evaluationValues);

    Long user(User user);
}
