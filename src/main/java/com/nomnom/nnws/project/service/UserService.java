package com.nomnom.nnws.project.service;

import com.nomnom.nnws.project.dto.UserDto;
import com.nomnom.nnws.project.enums.PreferenceType;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    UserDto createUser(UserDto userDto);
    void deleteUser(UUID id);
    UserDto getOrCreateUser(UUID id);
    public UserDto updatePreference(UUID id, PreferenceType preference);
}
