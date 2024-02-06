package com.baking.usersservice.controllers;
import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestUpdate;
import com.baking.usersservice.dto.response.UserResponse;
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
	public UserResponse findById(@PathVariable int id){
		return userService.findById(id);
	}

	@GetMapping
	public List<UserResponse> findAll(){
		List<UserResponse> res = userService.findAll();
		return res;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	public ResponseEntity createUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public UserResponse updateUser(@PathVariable int id, @RequestBody UserRequestUpdate user) {
		UserResponse dto = userService.updateUser(id, user);
		return dto;
	}

	@PatchMapping("/{id}")
	public void updateUserUno(@PathVariable int id, @RequestBody UserRequestUpdate user) {
		userService.updateUserUni(id, user);
	}

}
