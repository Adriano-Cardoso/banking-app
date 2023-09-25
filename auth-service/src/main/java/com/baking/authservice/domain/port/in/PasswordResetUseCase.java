package com.baking.authservice.domain.port.in;

public interface PasswordResetUseCase {

    void savePasswordResetToken(String email);
}
