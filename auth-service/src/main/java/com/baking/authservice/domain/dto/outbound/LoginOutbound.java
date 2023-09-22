package com.baking.authservice.domain.dto.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginOutbound {

    private String token;

    private String type;

}
