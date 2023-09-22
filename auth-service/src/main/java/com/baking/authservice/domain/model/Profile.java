package com.baking.authservice.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_profile")
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "name_profile", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String name;

    public Profile(String user) {
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
