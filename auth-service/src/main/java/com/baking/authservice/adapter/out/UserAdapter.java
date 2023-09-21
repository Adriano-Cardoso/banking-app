package com.baking.authservice.adapter.out;

import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.port.in.LoadUserByUserNameUseCase;
import com.baking.authservice.domain.port.out.LoadUserByUserNamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserAdapter implements LoadUserByUserNamePort {

    private final UserRepository userRepository;
    private final LoadUserByUserNameUseCase loadUserByUserNameUseCase;
    private final UserMapper userMapper;


    @Override
    public UserOutbound loadUserByUsername(String username) {
        UserOutbound userDetailsDTO = loadUserByUserNameUseCase.loadUserByUsername(username);

        if (userDetailsDTO != null) {
            return userDetailsDTO;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
