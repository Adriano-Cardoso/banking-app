package com.baking.usersservice.service;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.dto.request.UserRequestPutDto;
import com.baking.usersservice.dto.response.UserResponseDto;
import com.baking.usersservice.entities.User;
import com.baking.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepo;

    public UserResponseDto findById(int id) {
        User entity = userRepo.findById(id).get();
        UserResponseDto dto = new UserResponseDto(entity);
        return dto;
    }

    public List<UserResponseDto> findAll() {
        List<User> result = userRepo.findAll();
        List<UserResponseDto> dto = result.stream().map(x -> new UserResponseDto(x)).toList();
        return dto;
    }
    public UserResponseDto createUser(UserRequest userRequest) {
        User user = new User(userRequest);
        User entity = userRepo.save(user);
        UserResponseDto dto = new UserResponseDto(entity);
        return dto;
    }
    public void delProduto(int id) {
        userRepo.deleteById(id);
    }
    public UserResponseDto updateUser(int id, UserRequestPutDto userDto) {
        User user = new User(userDto);
        user.setUserId(id);
        User entity = userRepo.save(user);
        UserResponseDto dto = new UserResponseDto(entity);
        return dto;

    }
    public void updateUserUni(int id, UserRequestPutDto userDto) {
        User entity = userRepo.findById(id).get();
        String nome = userDto.getName();
        String email = userDto.getEmail();


        Optional.ofNullable(email).ifPresentOrElse(e -> {
            entity.setEmail(e);
            userRepo.save(entity);
            }, () -> Optional.ofNullable(nome).ifPresent(n -> {
                entity.setName(n);
                userRepo.save(entity);
            }));

        /*if(email != null){
            entity.setEmail(email);
            userRepo.save(entity);
        } else if (nome != null) {
            entity.setName(nome);
            userRepo.save(entity);
        }*/
    }
}



