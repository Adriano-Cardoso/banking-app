package com.baking.authservice.domain.port.in;

import com.baking.authservice.domain.dto.outbound.UserOutbound;

public interface LoadUserByUserNameUseCase  {

    UserOutbound loadUserByUsername(String username);
}
