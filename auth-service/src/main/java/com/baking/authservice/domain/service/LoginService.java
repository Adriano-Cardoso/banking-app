package com.baking.authservice.domain.service;

import com.baking.authservice.application.security.JwtTokenProvider;
import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.port.in.LoginAuthenticationUseCase;
import com.baking.authservice.domain.port.out.LoginAuthenticationPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginAuthenticationUseCase {

    private final AuthenticationManager authenticationManager;
    private final LoginAuthenticationPort loginAuthenticationPort;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginOutbound auth(@Valid LoginInbound loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        loginAuthenticationPort.userAuthenticated(loginRequest.getEmail());

        String token = jwtTokenProvider.createToken(loginRequest.getEmail(),
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return new LoginOutbound(token, "Bearer");
    }

}
