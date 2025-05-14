package com.nomnom.nnws.project.dto;

import com.nomnom.nnws.project.enums.PreferenceType;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private PreferenceType preference;
}
