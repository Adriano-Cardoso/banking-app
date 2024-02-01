package com.baking.usersservice.service;

import com.baking.usersservice.dto.request.UserRequestDto;
import com.baking.usersservice.dto.response.UserResponseDto;
import com.baking.usersservice.entities.User;

import com.baking.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public UserResponseDto findById(int id){
        User entity = userRepo.findById(id).get();
        UserResponseDto dto = new UserResponseDto(entity);
        return dto;
    }

    public List<UserResponseDto> findAll(){
        List<User> result = userRepo.findAll();
        List<UserResponseDto> dto = result.stream().map(x -> new UserResponseDto(x)).toList();
        return dto;
    }

    public UserResponseDto addContato(UserRequestDto userDto){
        User user = new User(userDto);
        User entity = userRepo.save(user);
        UserResponseDto dto = new UserResponseDto(entity);
        return dto;
    }














     /* private final UserMapper userMapper;
    public List<UserResponse> listAllUsers(){
        List<User> userList = userRepo.findAll();
        return userMapper.toListResponse(userList);
    }

    public List<UserResponse> listById(){
    return null;
    }
    
    public UserResponse createUser(UserRequest userRequest){
        
       userRepo.findByEmail(userRequest.getEmail()).ifPresent(u -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });

        User user = userMapper.userRequestToUser(userRequest); 
        
       userRepo.save(user);
        
        
        return userMapper.userToResponse(user);
    }
//    @GetMapping
//    public List<UsuarioDto> findAll() {
//        List<UsuarioDto> resp = usuarioService.findAll();
//        return resp;
//
//    }
//    @PostMapping(value = "/users")
//    public Usuario addUsuario(@RequestBody Usuario usuario) {
//        usuario.setSenha(encoder.encode(usuario.getSenha()));
//        Usuario resposta = repo.save(usuario);
//        return resposta ;
//
//    }
//
//    @DeleteMapping(value="/users/{id}")
//    public String delProduto(@PathVariable int id){
//        repo.deleteById(id);
//        return"Usuário excluído com sucesso.";
//    }
//
//    @PutMapping(value = "/users")
//    public Usuario atualiUser(@RequestBody Usuario usuario) {
//        Usuario resp = repo.save(usuario);
//        return resp;
//    }

   @GetMapping(value="/users/{id}")
    public List<UsuarioDto> findById(@PathVariable int id){
        List<UsuarioDto> resp = usuarioService.findById(id);
        return resp;
    }*/
}
