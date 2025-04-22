package com.nomnom.nnws.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Allergen {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "allergen")
    private List<AllergenIngredient> ingredients;

    @OneToMany(mappedBy = "allergen")
    private List<UserAllergen> users;
}