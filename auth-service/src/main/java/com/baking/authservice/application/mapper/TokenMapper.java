package com.baking.authservice.application.mapper;

import br.com.baking.model.LoginRequest;
import br.com.baking.model.LoginResponse;
import com.baking.authservice.domain.dto.inbound.LoginInbound;
import com.baking.authservice.domain.dto.outbound.LoginOutbound;
import com.baking.authservice.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(target = "token", source = "token")
    @Mapping( target = "type", constant = Constants.BEARER)
    LoginOutbound mapToDto(String token);

    LoginInbound convertRequestToInbound(LoginRequest loginRequest);

    LoginResponse convertOutboundToResponse(LoginOutbound loginOutbound);
}
