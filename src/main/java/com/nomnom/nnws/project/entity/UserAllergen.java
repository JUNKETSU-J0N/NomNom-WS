package com.nomnom.nnws.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(UserAllergenId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAllergen {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "allergen_id", referencedColumnName = "id")
    private Allergen allergen;
}

