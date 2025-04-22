package com.nomnom.nnws.project.entity;

import com.nomnom.nnws.project.enums.EvaluationValue;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserRecipeId.class)
public class UserRecipe {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Recipe recipe;

    private String notes;

    @Enumerated(EnumType.STRING)
    private EvaluationValue evaluation;
}

