package com.nomnom.nnws.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
import com.nomnom.nnws.project.service.ShoppingListService;
import com.nomnom.nnws.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shoppinglist")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService listService;
    private final UserService appuserService;

    

    @PostMapping
    public ResponseEntity<ShoppingListResponse> create(@RequestBody ShoppingListRequest request) {
        return ResponseEntity.ok(listService.createShoppingList(request));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListResponse>> getAll() {
        return ResponseEntity.ok(listService.getAllShoppingLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> getById(@RequestHeader("Authorization")String authHeader,@PathVariable Long id) {   
        String userid = appuserService.getCurrentUserKeycloakId(authHeader);
        if (userid == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
        return ResponseEntity.ok(listService.getByKeycloakId(userid));}
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ShoppingListResponse> updateList(@PathVariable Long id, @RequestBody ShoppingListRequest request) {
        return ResponseEntity.ok(listService.updateShoppingList(id, request));
    }

    @DeleteMapping("/reset/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        listService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }
    
}
