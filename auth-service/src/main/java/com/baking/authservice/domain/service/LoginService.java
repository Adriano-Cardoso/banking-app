package com.baking.authservice.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baking.authservice.application.mapper.TokenMapper;
import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.model.Profile;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenMapper tokenMapper;

    @Value("${api.jwt.secret}")
    private String secretKey;

    @Value("${api.jwt.expiration}")
    private String expiration;

    public LoginOutbound authenticateAndGenerateToken(LoginInbound data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            // Authentication successful, generate token
            var user = (User) auth.getPrincipal();
            List<String> profiles = user.getProfiles().stream()
                    .map(Profile::getName)
                    .collect(Collectors.toList());
            String token = createToken(user.getEmail(), profiles);

            LoginOutbound tokenDto = tokenMapper.mapToDto(token);
            return new LoginOutbound(tokenDto.getToken(), tokenDto.getType());
        } catch (Exception e) {
            throw Message.IS_PRESENT_USER.asBusinessException();
        }
    }

    public String createToken(String username, List<String> profiles) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withClaim("profiles", profiles)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
