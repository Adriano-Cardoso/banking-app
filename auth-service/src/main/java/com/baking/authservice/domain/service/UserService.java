package com.baking.authservice.domain.service;

import com.baking.authservice.adapter.out.ProfileRepository;
import com.baking.authservice.adapter.out.UserRepository;
import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.Profile;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.in.PasswordResetUseCase;
import com.baking.authservice.domain.port.in.SaveUserUseCase;
import com.baking.authservice.domain.port.out.SavePasswordResetTokenPort;
import com.baking.authservice.domain.port.out.SaveUserPort;
import com.baking.authservice.domain.validation.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("LoginService")
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService, SaveUserUseCase, PasswordResetUseCase {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SaveUserPort saveUserPort;

    private final SavePasswordResetTokenPort passwordResetTokenPort;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("method=loadUserByUsername username={}", username);
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    public UserOutbound save(UserInbound userInbound) {

//        userRepository.findByEmail(userInbound.getEmail()).ifPresent(p -> {
//            throw Message.IS_PRESENT_USER.asBusinessException();
//        });

        Profile profile = profileRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default profile not found."));

        User user = userMapper.userInboundToUser(userInbound);
        String hashedPassword = passwordEncoder.encode(userInbound.getPassword());
        user.setPassword(hashedPassword);
        user.setProfiles(Collections.singletonList(profile));

        saveUserPort.save(user);

        log.info("method=save username={} email={}", userInbound.getUsername(), userInbound.getEmail());

        return userMapper.userToUserOutbound(user);
    }

    @Override
    public void savePasswordResetToken(String email) {

        passwordResetTokenPort.savePasswordResetToken(email);

    }

}
