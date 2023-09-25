package com.baking.authservice.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    //token jwt
    public static final String BEARER = "bearer";

    //reset password
    public static final String RESET_TOKEN = generateResetToken();

    // kafka
    public static final String RESET_PASSWORD_TOPIC = "token-reset-password-topic";




    private static String generateResetToken() {
        return UUID.randomUUID().toString();
    }

}
