package com.nomnom.nnws.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(AllergenIngredientId.class)
public class AllergenIngredient {
    @Id
    @ManyToOne
    private Ingredient ingredient;

    @Id
    @ManyToOne
    private Allergen allergen;
}
