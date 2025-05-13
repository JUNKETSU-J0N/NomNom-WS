package com.nomnom.nnws.project.controller;

import com.nomnom.nnws.project.dto.UserAllergenDto;
import com.nomnom.nnws.project.service.UserAllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-allergens")
@RequiredArgsConstructor
public class UserAllergenController {

    private final UserAllergenService service;

    @PostMapping
    public ResponseEntity<UserAllergenDto> addUserAllergen(@RequestBody UserAllergenDto dto) {
        return ResponseEntity.ok(service.addUserAllergen(dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserAllergen(@RequestBody UserAllergenDto dto) {
        service.deleteUserAllergen(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserAllergenDto>> getAllByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }
}
