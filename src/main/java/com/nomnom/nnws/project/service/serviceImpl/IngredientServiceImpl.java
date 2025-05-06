package com.nomnom.nnws.project.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nomnom.nnws.project.dto.IngredientRequest;
import com.nomnom.nnws.project.dto.IngredientResponse;
import com.nomnom.nnws.project.entity.Ingredient;
import com.nomnom.nnws.project.mapper.IngredientMapper;
import com.nomnom.nnws.project.repository.IngredientRepository;
import com.nomnom.nnws.project.service.IngredientService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepo;
    private final IngredientMapper mapper;
    
    @Transactional
    @Override
    public IngredientResponse createIngredient(IngredientRequest request) {

        Ingredient ingredient = new Ingredient();
        ingredient.setName(request.getName());
        ingredient.setType(request.getType());
        ingredient.setUnit(request.getUnit());
        ingredient = ingredientRepo.save(ingredient);

        return mapper.toResponse(ingredient);
    }

    @Override
    public List<IngredientResponse> getAllIngredients() {
        return ingredientRepo.findAll().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public IngredientResponse getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        return mapper.toResponse(ingredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientRepo.existsById(id)) {
            throw new RuntimeException("Ingredient not found");
        }

        ingredientRepo.deleteById(id);
    }

    @Override
    public IngredientResponse updateIngredient(Long id, IngredientRequest request) {
        Ingredient ingredient = ingredientRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        ingredient.setName(request.getName());
        ingredient.setType(request.getType());
        ingredient.setUnit(request.getUnit());

        return mapper.toResponse(ingredient);
    }
    
}
