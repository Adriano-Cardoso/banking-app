package com.baking.authservice.domain.port.in;

import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import jakarta.validation.Valid;

public interface LoginAuthenticationUseCase {
    LoginOutbound auth(@Valid LoginInbound loginRequest);
}
