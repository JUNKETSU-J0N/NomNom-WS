package com.nomnom.nnws.project.service;

import java.util.List;
import java.util.UUID;

import com.nomnom.nnws.project.dto.ShoppingItemDto;
import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;

public interface ShoppingListService {
    ShoppingListResponse createShoppingList(ShoppingListRequest request);
    List<ShoppingListResponse> getAllShoppingLists();
    ShoppingListResponse getShoppingListById(Long id);
    ShoppingListResponse getShoppingListByUserId(UUID id);
    void deleteShoppingList(UUID userId);
    void deleteShoppingItem(Long id);
    ShoppingListResponse updateShoppingList(Long id, ShoppingListRequest request);
    ShoppingListResponse addItemToShoppingList(UUID userId, ShoppingItemDto itemRequest);
    void updateShoppingItem(ShoppingItemDto itemRequest);
}
