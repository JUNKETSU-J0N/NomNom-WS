package com.nomnom.nnws.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nomnom.nnws.project.entity.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

  @Query("SELECT sl FROM ShoppingList sl WHERE sl.user.keycloak_id = :keycloak_id")
  List<ShoppingList> getByKeycloakId(@Param("keycloak_id") String keycloak_id);
}
