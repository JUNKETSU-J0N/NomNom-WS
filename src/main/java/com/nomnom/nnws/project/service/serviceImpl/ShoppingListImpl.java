package com.nomnom.nnws.project.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
                    .added(dto.isAdded())
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
    public ShoppingListResponse getShoppingListById(Long id) {
        ShoppingList list = listrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("List not found"));
        return mapper.toResponse(list);
    }

    @Override
    public void deleteShoppingList(UUID userId) {
        List<ShoppingList> lists = listrepo.findByUserId(userId);
        if (lists == null || lists.size() == 0) {
            throw new RuntimeException("List not found");
        }
        itemrepo.deleteAllByShoppingListId(lists.get(0).getId());
        listrepo.deleteById(lists.get(0).getId());
        ShoppingList list = new ShoppingList();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        list.setUser(user);

        list = listrepo.save(list);
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
                    .added(dto.isAdded())
                    .build();

            newItems.add(itemrepo.save(item));
        }

        list.setItems(newItems);
        return mapper.toResponse(list);
    }

    @Override
    public ShoppingListResponse getShoppingListByUserId(UUID id) {
        List<ShoppingList> lists = listrepo.findByUserId(id);
        if (lists == null || lists.size() == 0) {

            throw new RuntimeException("Einkaufsliste nicht gefunden");
        } else {
            ShoppingList list = lists.get(0);
            return mapper.toResponse(list);
        }
    }

    @Override
    public ShoppingListResponse addItemToShoppingList(UUID userId, ShoppingItemDto itemRequest) {
        List<ShoppingList> lists = listrepo.findByUserId(userId);
        if (lists == null || lists.size() == 0) {
            throw new RuntimeException("Einkaufsliste nicht gefunden");
        } else {
            ShoppingList list = lists.get(0);
            Ingredient ingredient;

            ingredient = new Ingredient();
            ingredient.setId(null);
            ingredient.setName(itemRequest.getIngredient().getName());
            ingredient.setType(itemRequest.getIngredient().getType());
            ingredient.setUnit(itemRequest.getIngredient().getUnit());

            ingredient = ingredientRepo.save(ingredient);

            ShoppingItem item = ShoppingItem.builder().id(null)
                    .shoppingList(list)
                    .ingredient(ingredient)
                    .amount(itemRequest.getAmount())
                    .unit(itemRequest.getUnit())
                    .added(itemRequest.isAdded())
                    .build();

            itemrepo.save(item);
            return mapper.toResponse(list);
        }
    }

    @Override
    public void deleteShoppingItem(Long id) {
        if (!itemrepo.existsById(id)) {
            throw new RuntimeException("ShoppingItem not found");
        }
        itemrepo.deleteById(id);
    }

    @Override
    public void updateShoppingItem(ShoppingItemDto itemRequest) {
        if (itemrepo.existsById(itemRequest.getId())) {
            ShoppingItem item = itemrepo.findById(itemRequest.getId())
                    .orElseThrow(() -> new RuntimeException("ShoppingItem not found"));
            item.setAdded(itemRequest.isAdded());
            itemrepo.save(item);
        }
    }

}