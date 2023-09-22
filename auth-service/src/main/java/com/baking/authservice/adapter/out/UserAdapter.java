package com.baking.authservice.adapter.out;

import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.out.LoginAuthenticationPort;
import com.baking.authservice.domain.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, LoginAuthenticationPort {
    private final UserRepository userRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void userAuthenticated(String email) {
        log.info("Usuário autenticado: " + email);

    }
}
