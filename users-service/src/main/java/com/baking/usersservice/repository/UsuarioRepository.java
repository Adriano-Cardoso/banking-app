package com.baking.usersservice.repository;

import com.baking.usersservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    public Optional<Usuario> findById(Integer id);
}
