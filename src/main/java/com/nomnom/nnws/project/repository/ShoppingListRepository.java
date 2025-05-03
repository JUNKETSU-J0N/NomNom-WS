package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nomnom.nnws.project.entity.ShoppingItem;

public interface ShoppingListRepository extends JpaRepository<ShoppingItem, Long> {
    
}
