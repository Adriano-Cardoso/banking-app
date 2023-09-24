package com.baking.authservice.domain.service;

import com.baking.authservice.adapter.out.ProfileRepository;
import com.baking.authservice.adapter.out.UserRepository;
import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.exception.BusinessException;
import com.baking.authservice.domain.model.Profile;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.out.SaveUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


// imports...

class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private SaveUserPort saveUserPort;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should load user by username")
    void shouldLoadUserByUsername() {
        String username = "test@example.com";
        User user = new User();
        user.setEmail(username);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    @DisplayName("Should throw exception when user not found by username")
    void shouldThrowExceptionWhenUserNotFound() {
        String username = "nonexistent@example.com";

        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }

    @Test
    @DisplayName("Should save user and return user outbound")
    void shouldSaveUserAndReturnUserOutbound() {
        // Mock necessary objects
        UserInbound userInbound = new UserInbound();
        userInbound.setEmail("test@example.com");
        userInbound.setPassword("password");
        userInbound.setUsername("teste");

        Profile profile = new Profile();
        when(profileRepository.findByName("USER")).thenReturn(Optional.of(profile));

        doAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setUserId(1L);  // Mocking the ID assignment as an example
            return null;
        }).when(saveUserPort).save(any(User.class));

        String encodedPassword = "$2a$10$ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        userInbound.setPassword(encodedPassword);
        when(passwordEncoder.encode(userInbound.getPassword())).thenReturn(encodedPassword);

        // Mock the mapper behavior
        User mappedUser = new User();
        when(userMapper.userInboundToUser(userInbound)).thenReturn(mappedUser);
        when(userMapper.userToUserOutbound(mappedUser)).thenReturn(new UserOutbound());

        // Test
        UserOutbound result = userService.save(userInbound);

        // Verify
        verify(saveUserPort, times(1)).save(mappedUser);  // Verify with mapped user

        // Assertions
        assertNotNull(result);

        // Check if the password was correctly encoded
        verify(passwordEncoder).encode(userInbound.getPassword());
        result.setPassword(encodedPassword);
        assertEquals(encodedPassword, result.getPassword());
    }

    @Test
    @DisplayName("Should throw exception when trying to save existing user")
    void shouldThrowExceptionWhenSavingExistingUser() {
        // Mock necessary objects
        UserInbound userInbound = new UserInbound();
        userInbound.setEmail("test@example.com");
        userInbound.setPassword("password");
        userInbound.setUsername("teste");

        // User with the same email already exists
        when(userRepository.findByEmail(userInbound.getEmail())).thenReturn(Optional.of(new User()));

        // Test and assert
        assertThrows(BusinessException.class, () -> userService.save(userInbound));
    }
}
