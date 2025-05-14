package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;


public interface  AllergenRepository extends JpaRepository<Allergen, Long> {}
