package com.nomnom.nnws.project.service;

import java.util.List;

import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;

public interface ShoppingListService {
    ShoppingListResponse createShoppingList(ShoppingListRequest request);
    List<ShoppingListResponse> getAllShoppingLists();
    ShoppingListResponse getShoppingListById(Long id);
    void deleteShoppingList(Long id);
    ShoppingListResponse updateShoppingList(Long id, ShoppingListRequest request);
}
