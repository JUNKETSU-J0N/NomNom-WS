package com.nomnom.nnws.project.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nomnom.nnws.project.dto.ShoppingItemDto;
import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
import com.nomnom.nnws.project.entity.Ingredient;
import com.nomnom.nnws.project.entity.ShoppingItem;
import com.nomnom.nnws.project.entity.ShoppingList;
import com.nomnom.nnws.project.entity.User;
import com.nomnom.nnws.project.mapper.ShoppingListMapper;
import com.nomnom.nnws.project.repository.IngredientRepository;
import com.nomnom.nnws.project.repository.ShoppingItemRepository;
import com.nomnom.nnws.project.repository.ShoppingListRepository;
import com.nomnom.nnws.project.repository.UserRepository;
import com.nomnom.nnws.project.service.ShoppingListService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingListImpl implements ShoppingListService {

    private final IngredientRepository ingredientRepo;
    private final ShoppingListRepository listrepo;
    private final ShoppingItemRepository itemrepo;
    private final UserRepository userRepo;
    private final ShoppingListMapper mapper;

    @Transactional
    @Override
    public ShoppingListResponse createShoppingList(ShoppingListRequest request) {
        List<ShoppingItem> shoppingItems = new ArrayList<>();

        ShoppingList list = new ShoppingList();

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        list.setUser(user);

        list = listrepo.save(list);

        for (ShoppingItemDto dto : request.getItems()) {
            Ingredient ingredient = ingredientRepo.findById(dto.getIngredient().getId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));

            ShoppingItem item = ShoppingItem.builder()
                    .shoppingList(list)
                    .ingredient(ingredient)
                    .amount(dto.getAmount())
                    .unit(dto.getUnit())
                    .added(dto.isAddad())
                    .build();

            shoppingItems.add(itemrepo.save(item));
        }

        list.setItems(shoppingItems);
        return mapper.toResponse(list);
    }

    @Override
    public List<ShoppingListResponse> getAllShoppingLists() {
        return listrepo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingListResponse getByKeycloakId(String keycloakId) {
        ShoppingList list = listrepo.getByKeycloakId(keycloakId).get(0);
        if (list == null) {
            throw new RuntimeException("Einkaufsliste nicht gefunden");
        }
        return mapper.toResponse(list);
    }

    @Override
    public void deleteShoppingList(Long id) {
        if (!listrepo.existsById(id)) {
            throw new RuntimeException("List not found");
        }

        itemrepo.deleteAllByShoppingListId(id);
        listrepo.deleteById(id);
    }

    @Transactional
    @Override
    public ShoppingListResponse updateShoppingList(Long id, ShoppingListRequest request) {
        ShoppingList list = listrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("List not found"));

        itemrepo.deleteAll(list.getItems());

        List<ShoppingItem> newItems = new ArrayList<>();

        for (ShoppingItemDto dto : request.getItems()) {
            Ingredient ingredient = ingredientRepo.findById(dto.getIngredient().getId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));

            ShoppingItem item = ShoppingItem.builder()
                    .shoppingList(list)
                    .ingredient(ingredient)
                    .amount(dto.getAmount())
                    .unit(dto.getUnit())
                    .added(dto.isAddad())
                    .build();

            newItems.add(itemrepo.save(item));
        }

        list.setItems(newItems);
        return mapper.toResponse(list);
    }

    @Override
    public ShoppingListResponse getShoppingListById(Long id, String keycloakId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShoppingListById'");
    }

}