package com.baking.authservice.domain.port.out;

public interface LoginAuthenticationPort {
    void userAuthenticated(String email);
}
