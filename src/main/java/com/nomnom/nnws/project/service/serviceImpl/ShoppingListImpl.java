/* package com.nomnom.nnws.project.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nomnom.nnws.project.dto.ShoppingListRequest;
import com.nomnom.nnws.project.dto.ShoppingListResponse;
import com.nomnom.nnws.project.entity.ShoppingItem;
import com.nomnom.nnws.project.entity.ShoppingList;
import com.nomnom.nnws.project.mapper.ShoppingListMapper;
import com.nomnom.nnws.project.repository.ShoppingItemRepository;
import com.nomnom.nnws.project.repository.ShoppingListRepository;
import com.nomnom.nnws.project.service.ShoppingListService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingListImpl implements ShoppingListService{

    private final ShoppingListRepository listrepo;
    private final ShoppingItemRepository itemrepo;
    private final ShoppingListMapper mapper;
    
    @Transactional
    @Override
    public ShoppingListRequest createShoppingList(ShoppingListRequest request) {
        List<ShoppingItem> shoppingItems = new ArrayList<>();

        ShoppingList list = new ShoppingList();
        //... user???

        throw new UnsupportedOperationException("Unimplemented method 'createShoppingList'");
    }

    @Override
    public List<ShoppingListResponse> getAllShoppingLists() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllShoppingLists'");
    }

    @Override
    public ShoppingListResponse getShoppingListById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShoppingListById'");
    }

    @Override
    public void deleteShoppingList(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShoppingList'");
    }

    @Override
    public ShoppingListResponse updateShoppingList(Long id, ShoppingListRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShoppingList'");
    }


    
} */