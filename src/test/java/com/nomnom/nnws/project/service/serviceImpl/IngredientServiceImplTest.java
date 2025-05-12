package com.nomnom.nnws.project.service.serviceImpl;

import com.nomnom.nnws.project.dto.IngredientRequest;
import com.nomnom.nnws.project.dto.IngredientResponse;
import com.nomnom.nnws.project.entity.Ingredient;
import com.nomnom.nnws.project.enums.IngredientType;
import com.nomnom.nnws.project.enums.Unit;
import com.nomnom.nnws.project.mapper.IngredientMapper;
import com.nomnom.nnws.project.repository.IngredientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private IngredientRepository ingredientRepo;
    private IngredientMapper mapper;
    private IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        ingredientRepo = mock(IngredientRepository.class);
        mapper = mock(IngredientMapper.class);
        service = new IngredientServiceImpl(ingredientRepo, mapper);
    }

    @Test
    void createIngredient_shouldReturnResponse() {
        IngredientRequest request = new IngredientRequest("Salt", IngredientType.SPICE, Unit.GRAM);
        Ingredient ingredient = new Ingredient(1L, "Salt", IngredientType.SPICE, Unit.GRAM);
        IngredientResponse response = new IngredientResponse(1L, "Salt", IngredientType.SPICE, Unit.GRAM);

        when(ingredientRepo.save(any(Ingredient.class))).thenReturn(ingredient);
        when(mapper.toResponse(any(Ingredient.class))).thenReturn(response);

        IngredientResponse result = service.createIngredient(request);

        assertEquals(response, result);
        verify(ingredientRepo).save(any(Ingredient.class));
        verify(mapper).toResponse(any(Ingredient.class));
    }

    @Test
    void getAllIngredients_shouldReturnList() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient(1L, "Milk", IngredientType.DAIRY, Unit.LITER),
                new Ingredient(2L, "Egg", IngredientType.EGG, Unit.PIECE)
        );

        List<IngredientResponse> responses = Arrays.asList(
                new IngredientResponse(1L, "Milk", IngredientType.DAIRY, Unit.LITER),
                new IngredientResponse(2L, "Egg", IngredientType.EGG, Unit.PIECE)
        );

        when(ingredientRepo.findAll()).thenReturn(ingredients);
        when(mapper.toResponse(ingredients.get(0))).thenReturn(responses.get(0));
        when(mapper.toResponse(ingredients.get(1))).thenReturn(responses.get(1));

        List<IngredientResponse> result = service.getAllIngredients();

        assertEquals(responses, result);
    }

    @Test
    void getIngredientById_shouldReturnResponse() {
        Ingredient ingredient = new Ingredient(1L, "Sugar", IngredientType.SWEETS, Unit.GRAM);
        IngredientResponse response = new IngredientResponse(1L, "Sugar", IngredientType.SWEETS, Unit.GRAM);

        when(ingredientRepo.findById(1L)).thenReturn(Optional.of(ingredient));
        when(mapper.toResponse(ingredient)).thenReturn(response);

        IngredientResponse result = service.getIngredientById(1L);

        assertEquals(response, result);
    }

    @Test
    void deleteIngredient_shouldRemoveEntity() {
        when(ingredientRepo.existsById(1L)).thenReturn(true);

        service.deleteIngredient(1L);

        verify(ingredientRepo).deleteById(1L);
    }

    @Test
    void updateIngredient_shouldReturnUpdatedResponse() {
        Ingredient existing = new Ingredient(1L, "Milk", IngredientType.DAIRY, Unit.LITER);
        IngredientRequest request = new IngredientRequest("Oat Milk", IngredientType.VEGAN_PRODUCTS, Unit.LITER);
        Ingredient updated = new Ingredient(1L, "Oat Milk", IngredientType.VEGAN_PRODUCTS, Unit.LITER);
        IngredientResponse response = new IngredientResponse(1L, "Oat Milk", IngredientType.VEGAN_PRODUCTS, Unit.LITER);

        when(ingredientRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(mapper.toResponse(existing)).thenReturn(response);

        IngredientResponse result = service.updateIngredient(1L, request);

        assertEquals(response, result);
    }
}
