package com.nomnom.nnws.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ShoppingListResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(listService.getShoppingListById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> updateList(@PathVariable Long id, @RequestBody ShoppingListRequest request) {
        return ResponseEntity.ok(listService.updateShoppingList(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        listService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }
    
}
