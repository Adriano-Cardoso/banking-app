package com.baking.usersservice.repository;

import com.baking.usersservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <User, Integer> {

    Optional<User> findByEmail(String email);
}
