package com.baking.usersservice.service;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.entities.User;
import com.baking.usersservice.exception.BusinessException;
import com.baking.usersservice.mapper.UserMapper;
import com.baking.usersservice.repository.UsuarioRepository;
import com.baking.usersservice.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create User - Success")
    void testCreateUser() {
        // Mock input
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");

        // Mock repository behavior
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Mock mapper behavior
        User user = new User();
        when(userMapper.userRequestToUser(userRequest)).thenReturn(user);
        when(userMapper.userToResponse(user)).thenReturn(new UserResponse());

        // Call the method to be tested
        UserResponse response = userService.createUser(userRequest);

        // Verify repository save method was called
        verify(usuarioRepository, times(1)).save(user);

        // Verify the response
        assertNotNull(response);
    }

    @Test
    @DisplayName("Create User - Email Already Exists")
    void testCreateUserWhenEmailAlreadyExists() {
        // Mock input
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");

        // Mock repository behavior when email already exists
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));

        // Call the method to be tested and assert BusinessException is thrown
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.createUser(userRequest);
        });

        assertEquals(Message.IS_PRESENT_USER.getMessage(), exception.getMessage());
    }
}
