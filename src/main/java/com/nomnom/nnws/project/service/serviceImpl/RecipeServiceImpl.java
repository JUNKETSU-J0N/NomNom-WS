package com.nomnom.nnws.project.service.serviceImpl;

import com.nomnom.nnws.project.dto.RecipeIngredientDto;
import com.nomnom.nnws.project.dto.RecipeRequest;
import com.nomnom.nnws.project.dto.RecipeResponse;
import com.nomnom.nnws.project.entity.Ingredient;
import com.nomnom.nnws.project.entity.Recipe;
import com.nomnom.nnws.project.entity.RecipeIngredient;
import com.nomnom.nnws.project.enums.EvaluationValue;
import com.nomnom.nnws.project.mapper.RecipeMapper;
import com.nomnom.nnws.project.repository.IngredientRepository;
import com.nomnom.nnws.project.repository.RecipeIngredientRepository;
import com.nomnom.nnws.project.repository.RecipeRepository;
import com.nomnom.nnws.project.service.RecipeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final RecipeIngredientRepository recipeIngredientRepo;
    private final RecipeMapper mapper;

    @Transactional
    @Override
    public RecipeResponse createRecipe(RecipeRequest request) {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setName(request.getName());
        recipe.setDescription(request.getDescription());
        recipe = recipeRepo.save(recipe);

        for (RecipeIngredientDto dto : request.getIngredients()) {
            Ingredient ingredient = ingredientRepo.findById(dto.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));

            RecipeIngredient ri = RecipeIngredient.builder()
                    .recipe(recipe)
                    .ingredient(ingredient)
                    .amount(dto.getAmount())
                    .unit(dto.getUnit())
                    .build();

            recipeIngredients.add(recipeIngredientRepo.save(ri));
        }

        recipe.setIngredients(recipeIngredients);
        return mapper.toResponse(recipe);
    }

    @Override
    public List<RecipeResponse> getAllRecipes() {
        return recipeRepo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeResponse getRecipeById(Long id) {
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        return mapper.toResponse(recipe);
    }

    @Transactional
    @Override
    public RecipeResponse updateRecipe(Long id, RecipeRequest request) {
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        recipe.setName(request.getName());
        recipe.setDescription(request.getDescription());

        recipeIngredientRepo.deleteAll(recipe.getIngredients());

        List<RecipeIngredient> newIngredients = new ArrayList<>();

        for (RecipeIngredientDto dto : request.getIngredients()) {
            Ingredient ingredient = ingredientRepo.findById(dto.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));

            RecipeIngredient ri = RecipeIngredient.builder()
                    .recipe(recipe)
                    .ingredient(ingredient)
                    .amount(dto.getAmount())
                    .unit(dto.getUnit())
                    .build();

            newIngredients.add(recipeIngredientRepo.save(ri));
        }

        recipe.setIngredients(newIngredients);
        return mapper.toResponse(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepo.existsById(id)) {
            throw new RuntimeException("Recipe not found");
        }

        recipeIngredientRepo.deleteAllByRecipeId(id);

        recipeRepo.deleteById(id);
    }

    @Override
    public List<RecipeResponse> searchByNameAndDescription(String searchTerm) {
        // Suchbegriffe aufsplitten (bei mehreren Leerzeichen)
        String[] searchTerms = searchTerm.split("\\s+");

        // Bei jedem Suchbegriff eine Abfrage starten
        List<Recipe> results = recipeRepo.findAll().stream()
                .filter(recipe -> {
                    boolean matches = true;
                    for (String term : searchTerms) {
                        matches &= (recipe.getName().toLowerCase().contains(term.toLowerCase()) ||
                                recipe.getDescription().toLowerCase().contains(term.toLowerCase()));
                    }
                    return matches;
                })
                .collect(Collectors.toList());

        // Mapping in Response-Objekte
        return results.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


    private boolean matchesPreference(Recipe recipe, UserPreference preference) {
        if (preference == null) return true;

        RecipeType type = recipe.getType();

        switch (preference.getPreferenceType()) {
            case VEGAN:
                return type == RecipeType.VEGAN;
            case VEGETARIAN:
                return type == RecipeType.VEGAN || type == RecipeType.VEGETARIAN;
            case MEAT_LOVER:
                return true; // alles erlaubt
            case PESCATARIAN:
                return type == RecipeType.VEGAN || type == RecipeType.VEGETARIAN || type == RecipeType.PESCATARIAN;
            case HIGH_PROTEIN:
                return true; // keine Einschränkungen, aber du kannst Bonuslogik einbauen
            default:
                return true;
        }
    }

    @Override
    public List<RecipeResponse> getAllRecipesFilteredAndShuffled(Long userId) {
        // Alle Rezepte laden
        List<Recipe> allRecipes = recipeRepo.findAll();

        // 1. Allergene: Hole verbotene Allergene des Users
        Set<Long> allergenIds = userAllergenRepo.findByUserIdAndActiveTrue(userId)
                .stream()
                .map(ua -> ua.getAllergen().getId())
                .collect(Collectors.toSet());

        // Hole alle Zutaten-IDs, die zu diesen Allergenen gehören
        Set<Long> forbiddenIngredientIds = allergenIngredientRepo.findByAllergenIdIn(allergenIds)
                .stream()
                .map(ai -> ai.getIngredient().getId())
                .collect(Collectors.toSet());

        // 2. Evaluation: Hole geblockte Rezepte des Nutzers
        Set<Long> blockedRecipeIds = userRecipeRepo.findByUserIdAndEvaluationValueIn(userId,
                        List.of(EvaluationValue.BLOCK, EvaluationValue.DISLIKE))
                .stream()
                .map(ur -> ur.getRecipe().getId())
                .collect(Collectors.toSet());

        // 3. Preference: Hole Nutzerpräferenz
        UserPreference preference = userPreferenceRepo.findByUserId(userId);

        // 4. Filterlogik: Nur Rezepte behalten, die keine verbotenen Zutaten oder Bewertungen haben
        List<Recipe> filteredRecipes = allRecipes.stream()
                .filter(recipe -> !blockedRecipeIds.contains(recipe.getId()))
                .filter(recipe -> recipe.getIngredients().stream()
                        .noneMatch(ri -> forbiddenIngredientIds.contains(ri.getIngredient().getId())))
                .filter(recipe -> matchesPreference(recipe, preference))
                .collect(Collectors.toList());

        // 5. Shuffle
        Collections.shuffle(filteredRecipes);

        return filteredRecipes.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
    }
