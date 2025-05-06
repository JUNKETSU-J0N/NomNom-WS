package com.nomnom.nnws.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nomnom.nnws.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
