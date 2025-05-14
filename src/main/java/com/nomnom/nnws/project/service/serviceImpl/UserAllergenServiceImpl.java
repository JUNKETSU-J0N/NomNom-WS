package com.nomnom.nnws.project.service.serviceImpl;

import com.nomnom.nnws.project.dto.UserAllergenDto;
import com.nomnom.nnws.project.entity.Allergen;
import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.entity.UserAllergen;
import com.nomnom.nnws.project.mapper.UserAllergenMapper;
import com.nomnom.nnws.project.repository.AllergenRepository;
import com.nomnom.nnws.project.repository.UserAllergenRepository;
import com.nomnom.nnws.project.repository.UserRepository;
import com.nomnom.nnws.project.service.UserAllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAllergenServiceImpl implements UserAllergenService {

    private final UserRepository userRepository;
    private final AllergenRepository allergenRepository;
    private final UserAllergenRepository userAllergenRepository;
    private final UserAllergenMapper mapper;

    @Override
    public UserAllergenDto addUserAllergen(UserAllergenDto dto) {
        User user = userRepository.findById(UUID.fromString(dto.getUserId().toString()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Allergen allergen = allergenRepository.findById(dto.getAllergenId())
                .orElseThrow(() -> new IllegalArgumentException("Allergen not found"));

        UserAllergen entity = mapper.toEntity(user, allergen);
        return mapper.toDto(userAllergenRepository.save(entity));
    }

    @Override
    public void deleteUserAllergen(UserAllergenDto dto) {
        userAllergenRepository.deleteByUserIdAndAllergenId(
                UUID.fromString(dto.getUserId().toString()),
               dto.getAllergenId()
        );
    }

    @Override
    public List<UserAllergenDto> getAllByUserId(UUID userId) {
        return userAllergenRepository.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
