package com.nomnom.nnws.project.entity;

import com.nomnom.nnws.project.enums.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ShoppingList shoppingList;

    @ManyToOne
    private Ingredient ingredient;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private boolean added;
}


