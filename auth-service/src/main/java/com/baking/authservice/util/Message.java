package com.baking.authservice.util;

import com.baking.authservice.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    NOT_FOUND_VIDEO("O Usuário nao foi encontrado", HttpStatus.NOT_FOUND),
    IS_PRESENT_USER("O usuário já existe", HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("O Perfil selecionado nâo existe", HttpStatus.NOT_FOUND),
    TOKEN_ERROR("Token invalido", HttpStatus.FORBIDDEN),
    NOT_FOT_USER_PERMISSION("Usuário sem permissão", HttpStatus.FORBIDDEN),
    SERIALIZATION_MESSAGE_ERROR("Erro ao serializar a mensagem para JSON", HttpStatus.INTERNAL_SERVER_ERROR);

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
