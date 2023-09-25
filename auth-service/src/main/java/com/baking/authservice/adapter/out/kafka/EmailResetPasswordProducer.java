package com.baking.authservice.adapter.out.kafka;

import com.baking.authservice.domain.port.out.EmailSendCallbackPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
@RequiredArgsConstructor
public class EmailResetPasswordProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private final EmailSendCallbackPort emailSendCallbackPort;

    public void sendResetPasswordToken(String email, String token) {
        kafkaTemplate.send(TOPIC, email, token);
        emailSendCallbackPort.onEmailSent(email, token);
    }
}
