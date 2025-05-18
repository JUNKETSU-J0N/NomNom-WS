package com.nomnom.nnws.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nomnom.nnws.project.dto.ShoppingItemDto;
import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
import com.nomnom.nnws.project.service.ShoppingListService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shoppinglist")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService listService;

    

    @PostMapping
    public ResponseEntity<ShoppingListResponse> create(@RequestBody ShoppingListRequest request) {
        return ResponseEntity.ok(listService.createShoppingList(request));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListResponse>> getAll() {
        return ResponseEntity.ok(listService.getAllShoppingLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> getById(@PathVariable UUID id) {  
        return ResponseEntity.ok(listService.getShoppingListByUserId(id));}


    @PutMapping("/{userId}/items")
public ResponseEntity<Void> addItemToList(@PathVariable UUID userId, @RequestBody ShoppingItemDto itemRequest) {
    listService.addItemToShoppingList(userId, itemRequest);
    return ResponseEntity.ok().build();}
    

    @DeleteMapping("/reset/{userId}")
    public ResponseEntity<Void> deleteList( @PathVariable UUID userId) {
        listService.deleteShoppingList(userId);
        return ResponseEntity.noContent().build();
    }

        @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        listService.deleteShoppingItem(id);
        return ResponseEntity.noContent().build();
    }
            @PutMapping("/update/items")
    public ResponseEntity<Void> updateItem(@RequestBody ShoppingItemDto itemRequest){
        listService.updateShoppingItem(itemRequest);
        return ResponseEntity.noContent().build();
    }
}
