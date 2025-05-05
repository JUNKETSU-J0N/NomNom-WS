package com.nomnom.nnws.project.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nomnom.nnws.project.dto.UserRecipeRequest;
import com.nomnom.nnws.project.dto.UserRecipeResponse;
import com.nomnom.nnws.project.entity.UserRecipe;
import com.nomnom.nnws.project.entity.UserRecipeId;
import com.nomnom.nnws.project.mapper.UserRecipeMapper;
import com.nomnom.nnws.project.repository.RecipeRepository;
import com.nomnom.nnws.project.repository.UserRecipeRepository;
import com.nomnom.nnws.project.repository.UserRepository;
import com.nomnom.nnws.project.service.UserRecipeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRecipeServiceImpl implements UserRecipeService {
    //ich übernehme keine haftung für diesen service
    
    private final UserRecipeRepository urRepo;
    private final UserRepository userRepo;
    private final RecipeRepository recipeRepo;
    private final UserRecipeMapper mapper;

    @Transactional
    @Override
    public UserRecipeResponse createUserRecipe(UserRecipeRequest request) {
        
        

    }

    @Override
    public List<UserRecipeResponse> getAllUserRecipes() {
        return urRepo.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserRecipeResponse getUserRecipeByUserIdAndRecipeId(Long userId, Long recipeId) {
        UserRecipeId urId = new UserRecipeId(userId, recipeId);
        UserRecipe ur = urRepo.findById(urId)
            .orElseThrow(() -> new RuntimeException("Userrecipe not found"));
        return mapper.toResponse(ur); 
    }

    @Override
    public void deleteUserRecipe(Long userId, Long recipeId) {
        UserRecipeId urId = new UserRecipeId(userId, recipeId);

        if (!urRepo.existsById(urId)) {
            throw new RuntimeException("Userrecipe not found");
        }

        urRepo.deleteById(urId);
    }

    @Transactional
    @Override
    public UserRecipeResponse updateUserRecipe(Long userId, Long recipeId, UserRecipeRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserRecipe'");
    }
    
}
