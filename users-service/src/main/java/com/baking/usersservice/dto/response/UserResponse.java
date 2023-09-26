package com.baking.usersservice.dto.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private String resetToken;
    private String phone;
    private Integer bankAccount;
}
