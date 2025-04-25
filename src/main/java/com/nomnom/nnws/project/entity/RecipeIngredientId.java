package com.nomnom.nnws.project.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientId implements Serializable {
    private Long recipe;
    private Long ingredient;
}
