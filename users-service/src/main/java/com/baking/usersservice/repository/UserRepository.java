package com.baking.usersservice.repository;

import com.baking.usersservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByName(String name);
}
