package com.baking.usersservice.exception;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private final HttpStatus httpStatusCode;

    private final String code;

    private final String message;

    private final String description;

    public BusinessExceptionBody getOnlyBody() {
        return BusinessExceptionBody.builder().code(this.code).message(this.message).description(this.description)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BusinessExceptionBody {
        private String code;

        private String message;

        private String description;

    }
}
