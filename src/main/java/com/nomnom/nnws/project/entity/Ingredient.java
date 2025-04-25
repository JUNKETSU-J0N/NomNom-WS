package com.nomnom.nnws.project.entity;

import com.nomnom.nnws.project.enums.IngredientType;
import com.nomnom.nnws.project.enums.Unit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType type;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
