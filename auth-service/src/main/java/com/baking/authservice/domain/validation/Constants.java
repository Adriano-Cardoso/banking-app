package com.baking.authservice.domain.validation;

import lombok.Data;

import java.util.UUID;

@Data
public class Constants {

    public static final String BEARER = "bearer";
    public static final String
            RESET_TOKEN = generateResetToken();

    private static String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
