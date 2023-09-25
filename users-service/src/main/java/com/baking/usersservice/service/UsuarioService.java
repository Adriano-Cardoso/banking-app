package com.baking.usersservice.service;

import com.baking.usersservice.dto.UsuarioDto;
import com.baking.usersservice.entities.Usuario;
import com.baking.usersservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository  usuarioRepository;

    public List<UsuarioDto> findAll(){
        List<Usuario> result = usuarioRepository.findAll();
        List<UsuarioDto> dto = result.stream().map(x -> new UsuarioDto(x)).toList();
        return dto;

    }
}
