package com.nomnom.nnws.project.service;

import com.nomnom.nnws.project.dto.UserAllergenDto;

import java.util.List;
import java.util.UUID;

public interface UserAllergenService {
    UserAllergenDto addUserAllergen(UserAllergenDto dto);
    void deleteUserAllergen(UserAllergenDto dto);
    List<UserAllergenDto> getAllByUserId(UUID userId);
}
