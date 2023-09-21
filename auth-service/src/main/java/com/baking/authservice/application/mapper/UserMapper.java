package com.baking.authservice.application.mapper;

import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.User;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {

    UserOutbound userEntityToUserDTO(User user);
    User userDTOToUserEntity(UserInbound userInbound);
    UserDetails userDTOToUserDetails(UserOutbound userDTO);

    UserDetails userDTOToUserDetails(User user);
}
