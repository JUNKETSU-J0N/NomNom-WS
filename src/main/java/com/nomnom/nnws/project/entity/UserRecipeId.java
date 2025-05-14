package com.nomnom.nnws.project.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecipeId implements Serializable {
    private UUID user;
    private Long recipe;
}
