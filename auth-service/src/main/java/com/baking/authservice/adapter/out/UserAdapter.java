package com.baking.authservice.adapter.out;

import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.in.LoadUserByUserNameUseCase;
import com.baking.authservice.domain.port.out.LoadUserByUserNamePort;
import com.baking.authservice.domain.port.out.LoginAuthenticationPort;
import com.baking.authservice.domain.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class UserAdapter implements LoadUserByUserNamePort, SaveUserPort, LoginAuthenticationPort {

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

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void userAuthenticated(String email) {
        log.info("Usuário autenticado: " + email);

    }
}
