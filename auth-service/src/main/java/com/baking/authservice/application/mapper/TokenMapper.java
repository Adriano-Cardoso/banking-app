package com.baking.authservice.application.mapper;

import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.domain.validation.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(target = "token", source = "token")
    @Mapping( target = "type", constant = Constants.BEARER)
    LoginOutbound mapToDto(String token);
}
