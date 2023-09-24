package com.baking.authservice.adapter.in.web;

import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginOutbound> authenticateAndGenerateToken(@RequestBody LoginInbound loginInbound) {
        return ResponseEntity.ok(loginService.authenticateAndGenerateToken(loginInbound));
    }
}
