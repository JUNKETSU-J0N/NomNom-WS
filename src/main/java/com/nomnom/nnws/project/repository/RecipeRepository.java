package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r WHERE " +
            "(LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) ")
    List<Recipe> searchByNameAndDescription(String searchTerm);

    @Query("SELECT r FROM Recipe r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "AND LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Recipe> searchByMultipleTerms(String searchTerm);}

