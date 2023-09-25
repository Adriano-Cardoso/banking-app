package com.baking.authservice.adapter.out.kafka;

import com.baking.authservice.domain.dto.ResetPasswordMessageDTO;
import com.baking.authservice.domain.port.out.EmailSendCallbackPort;
import com.baking.authservice.util.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailResetPasswordProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EmailSendCallbackPort emailSendCallbackPort;
    private final ObjectMapper objectMapper;

    public void sendResetPasswordToken(String email, String token) {
        try {
            ResetPasswordMessageDTO message = new ResetPasswordMessageDTO(email, token);
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("token-reset-password-topic", email, jsonMessage);
            emailSendCallbackPort.onEmailSent(email, token);
        } catch (JsonProcessingException e) {
            throw Message.SERIALIZATION_MESSAGE_ERROR.asBusinessException();
        }
    }
}
