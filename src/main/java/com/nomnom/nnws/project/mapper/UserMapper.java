package com.nomnom.nnws.project.mapper;

import com.nomnom.nnws.project.dto.UserDto;
import com.nomnom.nnws.project.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        return UserDto.builder()
                .id(user.getId())
                .preference(user.getPreference())
                .build();
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .preference(dto.getPreference())
                .build();
    }
}
