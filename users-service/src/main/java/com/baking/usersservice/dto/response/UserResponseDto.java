package com.baking.usersservice.dto.response;

import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private int userId;
    private String name;
    private String email;


    public UserResponseDto (User user){
        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();}
}
