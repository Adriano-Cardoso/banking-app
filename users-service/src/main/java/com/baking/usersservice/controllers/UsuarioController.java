package com.baking.usersservice.controllers;



import java.util.List;


import com.baking.usersservice.dto.UsuarioDto;

import com.baking.usersservice.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.baking.usersservice.entities.Usuario;
import com.baking.usersservice.repository.UsuarioRepository;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repo;

	private final PasswordEncoder encoder;
	@Autowired
	private UsuarioService usuarioService;


	@GetMapping
	public List<UsuarioDto> findAll() {
		List<UsuarioDto> resp = usuarioService.findAll();
		return resp;
	
	}
	@PostMapping(value = "/users")
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		Usuario resposta = repo.save(usuario);
		return resposta ;
		
	}
	
	@DeleteMapping(value="/users/{id}")
	public String delProduto(@PathVariable int id){
		repo.deleteById(id);
		return"Usuário excluído com sucesso.";
		}
	
	@PutMapping(value = "/users")
	public Usuario atualiUser(@RequestBody Usuario usuario) {
		Usuario resp = repo.save(usuario);
		return resp;
	}




}
