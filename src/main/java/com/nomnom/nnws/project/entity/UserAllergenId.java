package com.nomnom.nnws.project.entity;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllergenId implements Serializable {
    private UUID user;
    private Long allergen;
}

