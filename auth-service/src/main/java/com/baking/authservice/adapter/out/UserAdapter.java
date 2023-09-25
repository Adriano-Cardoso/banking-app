package com.baking.authservice.adapter.out;

import com.baking.authservice.adapter.out.kafka.EmailResetPasswordProducer;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.out.LoginAuthenticationPort;
import com.baking.authservice.domain.port.out.SavePasswordResetTokenPort;
import com.baking.authservice.domain.port.out.SaveUserPort;
import com.baking.authservice.util.Constants;
import com.baking.authservice.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, LoginAuthenticationPort, SavePasswordResetTokenPort {
    private final UserRepository userRepository;
    private final EmailResetPasswordProducer emailResetPasswordProducer;

    @Override
    public void save(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });
        userRepository.save(user);
    }

    @Override
    public void userAuthenticated(String email) {
        log.info("Usuário autenticado: " + email);

    }

    @Override
    public void savePasswordResetToken(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setResetToken(Constants.RESET_TOKEN);
            userRepository.save(user);
            log.info("Token de redefinição de senha salvo para o usuário: " + email);

            emailResetPasswordProducer.sendResetPasswordToken(email, Constants.RESET_TOKEN);
        });
    }


}
