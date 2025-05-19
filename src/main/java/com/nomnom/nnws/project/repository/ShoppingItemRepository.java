package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nomnom.nnws.project.entity.ShoppingItem;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

    @Modifying
    @Query("DELETE FROM ShoppingItem item WHERE item.shoppingList.id = :shoppingListId")
    void deleteAllByShoppingListId(@Param("shoppingListId") Long shoppingListId);

}
