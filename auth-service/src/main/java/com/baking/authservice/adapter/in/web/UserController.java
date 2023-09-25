package com.baking.authservice.adapter.in.web;

import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserOutbound> createUser(@Valid @RequestBody UserInbound userInbound) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userInbound));
    }

    @PostMapping("/password-reset")
    public ResponseEntity<Void> savePasswordResetToken(@RequestParam("email") String email) {
        userService.savePasswordResetToken(email);
        return ResponseEntity.ok().build();
    }
}
