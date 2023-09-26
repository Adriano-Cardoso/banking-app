package com.baking.usersservice.entities;

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
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String resetToken;
    private String phone;
    private Integer bankAccount;


}
