package com.nomnom.nnws.project.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nomnom.nnws.project.entity.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    
List<ShoppingList> findByUserId(UUID userId);
}
