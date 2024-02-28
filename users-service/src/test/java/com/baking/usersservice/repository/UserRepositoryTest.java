package com.baking.usersservice.repository;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.entities.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Usuário existente no DB")
    void findByEmailCase1() {
        String email = "dyeggo@gmail.com";
        UserRequest data = new UserRequest("Dyeggo",email,"123456789");
        this.createUser(data);

        Optional<User> result = this.userRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();

    }


    @Test
    @DisplayName("Usuário não existente no DB")
    void findByEmailCase2() {
        String email = "dyeggo@gmail.com";
        Optional<User> result = this.userRepository.findByEmail(email);
        assertThat(result.isEmpty()).isTrue();

    }


    private User createUser(UserRequest user){
        User newUser = new User(user);
        this.entityManager.persist(newUser);
        return newUser;
    }



}