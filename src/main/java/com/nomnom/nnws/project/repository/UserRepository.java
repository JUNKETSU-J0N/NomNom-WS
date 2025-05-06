package com.nomnom.nnws.project.repository;

import com.nomnom.nnws.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
