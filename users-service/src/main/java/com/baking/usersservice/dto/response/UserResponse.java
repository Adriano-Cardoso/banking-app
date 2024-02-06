package com.baking.usersservice.dto.response;

import com.baking.usersservice.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Integer userId;
    private String name;
    private String email;
    /*
    private String password;
    private String resetToken;
    private String phone;
    private Integer bankAccount;
     */

    public UserResponse (User user){
        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();
    }
}
