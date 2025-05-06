package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.UserAllergen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAllergenRepository extends JpaRepository<UserAllergen, Long> {
    List<UserAllergen> findByUserId(Long userId);
}
