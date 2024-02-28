package com.baking.usersservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;
    /*private String resetToken;
    private String phone;
    private Integer bankAccount;*/
}
