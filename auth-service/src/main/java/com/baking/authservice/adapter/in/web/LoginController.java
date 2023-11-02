package com.baking.authservice.adapter.in.web;

import br.com.baking.api.AuthApi;
import br.com.baking.model.LoginRequest;
import br.com.baking.model.LoginResponse;
import com.baking.authservice.application.mapper.TokenMapper;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController implements AuthApi {

    private final LoginService loginService;
    private final TokenMapper tokenMapper;


    @Override
    public ResponseEntity<LoginResponse> authenticateAndGenerateToken(LoginRequest loginRequest) {

        LoginOutbound loginOutbound = loginService.authenticateAndGenerateToken(tokenMapper.convertRequestToInbound(loginRequest));

        return ResponseEntity.status(HttpStatus.OK).body(tokenMapper.convertOutboundToResponse(loginOutbound));
    }

}
