package com.baking.usersservice.mapper;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestToUser(UserRequest userRequest);

    UserResponse userToResponse(User user);

    List<UserResponse> toListResponse(List<User> userList);
}
