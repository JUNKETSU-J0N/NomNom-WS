package com.nomnom.nnws.project.service.serviceImpl;

import com.nomnom.nnws.project.dto.UserDto;
import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.enums.PreferenceType;
import com.nomnom.nnws.project.mapper.UserMapper;
import com.nomnom.nnws.project.repository.UserRepository;
import com.nomnom.nnws.project.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getOrCreateUser(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .id(id)
                            .preference(PreferenceType.NONE)
                            .build();
                    User savedUser = userRepository.save(newUser);
                    return UserMapper.toDto(savedUser);
                });
    }

    @Override
    public UserDto updatePreference(UUID id, PreferenceType preference) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        user.setPreference(preference);
        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }
}
