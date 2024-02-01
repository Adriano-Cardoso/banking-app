package com.baking.usersservice.controllers;


import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.dto.response.UserResponseDto;
import com.baking.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
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
	public UserResponseDto addUser(@RequestBody UserRequestDto contato){
		UserResponseDto dto = userService.addUser(contato);
		return dto;
	}
	@DeleteMapping(value="/{id}")
	public String delUser(@PathVariable int id) {
		userService.delProduto(id);
		return "message: Usuário excluído com sucesso.";
	}









	/*@GetMapping
	public ResponseEntity<List<UserResponse>> listAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> listUsersById(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
	}


	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}*/
}
