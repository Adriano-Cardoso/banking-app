package com.baking.usersservice.entities;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.request.UserRequestUpdate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;

    private String email;
    private String password;

public User (UserRequest userDto){
    name = userDto.getName();
    email = userDto.getEmail();
    password = userDto.getPassword();
}

    public User (UserRequestUpdate userDto){
        name = userDto.getName();
        email = userDto.getEmail();
    }


}
