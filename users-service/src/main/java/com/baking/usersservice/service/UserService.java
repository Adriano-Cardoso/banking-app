package com.baking.usersservice.service;

import com.baking.usersservice.dto.request.UserRequest;
import com.baking.usersservice.dto.response.UserResponse;
import com.baking.usersservice.entities.User;
import com.baking.usersservice.mapper.UserMapper;
import com.baking.usersservice.repository.UsuarioRepository;
import com.baking.usersservice.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository  usuarioRepository;
    private final UserMapper userMapper;
    

    public List<UserResponse> listAllUsers(){
        List<User> userList = usuarioRepository.findAll();
        return userMapper.toListResponse(userList);

    }
    
    public UserResponse createUser(UserRequest userRequest){
        
        usuarioRepository.findByEmail(userRequest.getEmail()).ifPresent(u -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });

        User user = userMapper.userRequestToUser(userRequest); 
        
        usuarioRepository.save(user);
        
        
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
}
