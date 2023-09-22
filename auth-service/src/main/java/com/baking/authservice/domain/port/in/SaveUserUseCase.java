package com.baking.authservice.domain.port.in;

import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;

public interface SaveUserUseCase {

    UserOutbound save(UserInbound userInbound);
}
