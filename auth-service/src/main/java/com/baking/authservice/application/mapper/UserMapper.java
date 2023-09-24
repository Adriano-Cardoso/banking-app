package com.baking.authservice.application.mapper;

import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "userInbound.username")
    @Mapping(target = "email", source = "userInbound.email")
    @Mapping(target = "userId", ignore = true)
    User userInboundToUser(UserInbound userInbound);


    UserOutbound userToUserOutbound(User user);


}
