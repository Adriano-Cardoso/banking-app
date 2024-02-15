package com.baking.usersservice.service;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestUpdate;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.entities.User;
import com.baking.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserResponse findById(int id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));
        return  new UserResponse(entity);
    }

    public List<UserResponse> findAll() {
        List<User> result = userRepository.findAll();
        List<UserResponse> dto = result.stream().map(user -> new UserResponse(user)).toList();
        return dto;
    }
    public UserResponse createUser(UserRequest userRequest) {

       if (userRepository.existsByName(userRequest.getName()) == true) {
            throw new IllegalArgumentException("Usuário com o nome de usuário '" + userRequest.getName() + "' existe.");
        }

        User user = new User(userRequest);
        User entity = userRepository.save(user);
        UserResponse dto = new UserResponse(entity);
        return dto;
    }
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);

    }
    public UserResponse updateUser(int id, UserRequestUpdate userDto) {
        User user = new User(userDto);
        user.setUserId(id);
        User entity = userRepository.save(user);
        UserResponse dto = new UserResponse(entity);
        return dto;

    }
    public void updateUserUni(int id, UserRequestUpdate userDto) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));

        Optional.ofNullable(userDto.getEmail()).ifPresent(entity::setEmail);
        Optional.ofNullable(userDto.getName()).ifPresent(entity::setName);

            userRepository.save(entity);



    }
}



