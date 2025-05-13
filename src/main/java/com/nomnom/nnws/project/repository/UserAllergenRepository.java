package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.UserAllergen;
import com.nomnom.nnws.project.entity.UserAllergenId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserAllergenRepository extends JpaRepository<UserAllergen, UserAllergenId> {
    List<UserAllergen> findByUserId(UUID userId);
}
