package com.baking.authservice.application.mapper;

import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.Profile;
import com.baking.authservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDetails userDTOToUserDetails(User user);

    @Mapping(target = "password", expression = "java(encodePassword(userInbound.getPassword()))")
    @Mapping(target = "profiles", expression = "java(createProfileList())")
    User userInboundToUser(UserInbound userInbound);

    UserOutbound userToUserOutbound(User user);

    default String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    default List<Profile> createProfileList() {
        List<Profile> listProfile = new ArrayList<>();
        listProfile.add(new Profile("USER"));  // Assuming you have a constructor in Profile
        return listProfile;
    }
}
