package com.nomnom.nnws.project.entity;

import com.nomnom.nnws.project.enums.PreferenceType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
public class User {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PreferenceType preference;

    @OneToMany(mappedBy = "user")
    private List<UserRecipe> recipes;

    @OneToMany(mappedBy = "user")
    private List<UserAllergen> allergens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingList shoppingList;
}
