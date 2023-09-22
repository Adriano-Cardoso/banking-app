package com.baking.authservice.adapter.in.web;

import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@AllArgsConstructor
@RestController
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginOutbound> auth(@RequestBody LoginInbound loginInbound) {
        return ResponseEntity.ok(loginService.auth(loginInbound));
    }
}
