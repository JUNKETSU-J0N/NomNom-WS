package com.nomnom.nnws.project.entity;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllergenId implements Serializable {
    private Long user;
    private Long allergen;
}

