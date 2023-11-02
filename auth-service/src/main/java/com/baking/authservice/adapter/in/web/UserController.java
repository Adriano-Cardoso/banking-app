package com.baking.authservice.adapter.in.web;

import br.com.baking.api.UsersApi;
import com.baking.authservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserOutbound> createUser(@Valid @RequestBody UserInbound userInbound) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userInbound));
//    }
//
//    @PostMapping("/password-reset")
//    public ResponseEntity<Void> savePasswordResetToken(@RequestParam("email") String email) {
//        userService.savePasswordResetToken(email);
//        return ResponseEntity.ok().build();
//    }
}
