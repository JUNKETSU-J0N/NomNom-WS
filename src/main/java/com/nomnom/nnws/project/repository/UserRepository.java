package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
}
