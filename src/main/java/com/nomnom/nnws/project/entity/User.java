package com.nomnom.nnws.project.entity;

import com.nomnom.nnws.project.enums.PreferenceType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appuser")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private PreferenceType preference;

    @OneToMany(mappedBy = "user")
    private List<UserRecipe> recipes;

    @OneToMany(mappedBy = "user")
    private List<UserAllergen> allergens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingList shoppingList;
}
