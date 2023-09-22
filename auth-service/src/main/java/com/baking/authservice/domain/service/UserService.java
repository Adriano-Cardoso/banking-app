package com.baking.authservice.domain.service;

import com.baking.authservice.adapter.out.ProfileRepository;
import com.baking.authservice.adapter.out.UserRepository;
import com.baking.authservice.application.mapper.UserMapper;
import com.baking.authservice.domain.dto.inbound.UserInbound;
import com.baking.authservice.domain.dto.outbound.UserOutbound;
import com.baking.authservice.domain.model.Profile;
import com.baking.authservice.domain.model.User;
import com.baking.authservice.domain.port.in.SaveUserUseCase;
import com.baking.authservice.domain.port.out.LoadUserByUserNamePort;
import com.baking.authservice.domain.port.out.SaveUserPort;
import com.baking.authservice.domain.validation.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService, SaveUserUseCase {

    private final LoadUserByUserNamePort loadUserByUsernamePort;
    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final SaveUserPort saveUserPort;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return userMapper.userDTOToUserDetails(user);
    }


    @Override
    public UserOutbound save(UserInbound userInbound) {
        userRepository.findByEmail(userInbound.getEmail())
                .ifPresent(p -> {
                    throw Message.IS_PRESENT_USER.asBusinessException();
                });

        Profile profile = profileRepository.findByName("USER")
                .orElseThrow(Message.NAME_PROFILE_NOT_FOUND::asBusinessException);

        List<Profile> listProfile = new ArrayList<>();
        listProfile.add(profile);

        User user = userMapper.userInboundToUser(userInbound);
        user.setProfiles(listProfile);

        saveUserPort.save(user);

        log.info("method=save username={} email={}", user.getUsername(), user.getEmail());

        return userMapper.userToUserOutbound(user);
    }

}
