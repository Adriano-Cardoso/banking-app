package com.baking.authservice.domain.port.out;

public interface EmailSendCallbackPort {

    void onEmailSent(String email, String token);

}
