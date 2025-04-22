package com.nomnom.nnws.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserAllergenId.class)
public class UserAllergen {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Allergen allergen;
}

