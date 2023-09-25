package com.baking.authservice.domain.port.out;

public interface SavePasswordResetTokenPort {

    void savePasswordResetToken(String email);
}
