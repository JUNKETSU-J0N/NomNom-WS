package com.nomnom.nnws.project.dto;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAllergenDto {
    private UUID userId;
    private long allergenId;
}
