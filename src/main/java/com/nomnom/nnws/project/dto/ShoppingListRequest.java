package com.nomnom.nnws.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListRequest {
    private Long userId;
    private List<ShoppingItemDto> items;
}
