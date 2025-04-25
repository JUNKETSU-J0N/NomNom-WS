package com.nomnom.nnws.project.entity;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllergenIngredientId implements Serializable {
    private Long ingredient;
    private Long allergen;
}

