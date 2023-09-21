package com.baking.authservice.domain.port.out;

import com.baking.authservice.domain.dto.outbound.UserOutbound;

public interface LoadUserByUserNamePort {

    UserOutbound loadUserByUsername(String username);

}
