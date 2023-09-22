package com.baking.authservice.domain.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInbound {

    private String username;
    private String email;
    private String password;
}
