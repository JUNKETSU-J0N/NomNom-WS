package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nomnom.nnws.project.entity.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    
}
