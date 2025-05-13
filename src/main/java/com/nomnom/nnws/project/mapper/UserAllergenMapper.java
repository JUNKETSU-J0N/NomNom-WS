package com.nomnom.nnws.project.mapper;

import com.nomnom.nnws.project.dto.UserAllergenDto;
import com.nomnom.nnws.project.entity.Allergen;
import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.entity.UserAllergen;
import org.springframework.stereotype.Component;

@Component
public class UserAllergenMapper {

    public UserAllergen toEntity( User user, Allergen allergen) {
        return UserAllergen.builder()
                .user(user)
                .allergen(allergen)
                .build();
    }

    public UserAllergenDto toDto(UserAllergen entity) {
        return UserAllergenDto.builder()
                .userId(entity.getUser().getId())
                .allergenId(entity.getAllergen().getId())
                .build();
    }
}
