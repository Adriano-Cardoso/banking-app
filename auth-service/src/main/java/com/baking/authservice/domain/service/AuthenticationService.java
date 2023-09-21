package com.baking.authservice.domain.service;

import com.baking.authservice.adapter.out.UserRepository;
import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.out.LoadUserByUserNamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final LoadUserByUserNamePort loadUserByUsernamePort;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return userMapper.userDTOToUserDetails(user);
    }
}
