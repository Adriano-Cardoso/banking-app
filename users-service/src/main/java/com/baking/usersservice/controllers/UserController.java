package com.baking.usersservice.controllers;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.dto.request.UserRequestPutDto;
import com.baking.usersservice.dto.response.UserResponseDto;
import com.baking.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	@Autowired
	private final UserService userService;

	@GetMapping(value  ="/{id}")
	public UserResponseDto findById(@PathVariable int id){
		return userService.findById(id);
	}

	@GetMapping
	public List<UserResponseDto> findAll(){
		List<UserResponseDto> res = userService.findAll();
		return res;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	public ResponseEntity createUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}
	@DeleteMapping(value="/{id}")
	public String delUser(@PathVariable int id) {
		userService.delProduto(id);
		return "message: Usuário excluído com sucesso.";

	}
	@PutMapping("/{id}")
	public UserResponseDto updateUser(@PathVariable int id, @RequestBody UserRequestPutDto user) {
		UserResponseDto dto = userService.updateUser(id, user);
		return dto;
	}

	@PatchMapping("/{id}")
	public void updateUserUno(@PathVariable int id, @RequestBody UserRequestPutDto user) {
		userService.updateUserUni(id, user);
	}

}
