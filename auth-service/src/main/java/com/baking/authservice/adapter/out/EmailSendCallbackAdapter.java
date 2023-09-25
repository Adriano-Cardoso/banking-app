package com.baking.authservice.adapter.out;

import com.baking.authservice.domain.port.out.EmailSendCallbackPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSendCallbackAdapter implements EmailSendCallbackPort {

    @Override
    public void onEmailSent(String email, String token) {
        log.info("E-mail com token de redefinição de senha enviado para: " + email);

    }
}
