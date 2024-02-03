package com.baking.usersservice.entities;

import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.dto.request.UserRequestPutDto;
import com.baking.usersservice.dto.response.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public User (UserRequestPutDto userDto){
        name = userDto.getName();
        email = userDto.getEmail();
    }


}
