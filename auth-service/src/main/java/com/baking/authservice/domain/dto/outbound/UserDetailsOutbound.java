package com.baking.authservice.domain.dto.outbound;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDetailsOutbound {

    private String name;
    private String email;
    private String password;
}
