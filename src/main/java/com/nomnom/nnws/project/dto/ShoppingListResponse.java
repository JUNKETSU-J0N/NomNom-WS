package com.nomnom.nnws.project.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListResponse {
    private Long id;
    private UUID userId;
    private List<ShoppingItemDto> items;
}
