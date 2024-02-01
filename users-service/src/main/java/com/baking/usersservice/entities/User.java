package com.baking.usersservice.entities;

import com.baking.usersservice.dto.request.UserRequestDto;
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

public User (UserRequestDto userDto){
    name = userDto.getName();
    email = userDto.getEmail();
    password = userDto.getPassword();

}

}
