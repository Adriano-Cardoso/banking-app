package com.baking.usersservice.controllers;


import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> listAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
	}
	


}
