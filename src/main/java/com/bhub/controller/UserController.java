package com.bhub.controller;

import com.bhub.domain.User;
import com.bhub.dto.UserDto;
import com.bhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDto>> listUsers(){
        List<UserDto> response = new ArrayList<>(List.of());
        userService.listUsers().forEach(user -> response.add(UserDto.toDto(user)));
        if(!response.isEmpty()){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable Long userId) throws Exception {
        try {
            Optional<User> response = userService.findById(userId);
            if(response.isPresent()){
                return ResponseEntity.ok().body(UserDto.toDto(response.get()));
            } else {
                throw new Exception("Usuário não encontrado!");
            }
        } catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.toDto(userService.createUser(userDto)));
        } catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) throws Exception {
        try {
            //userService.updateUser(userId, userDto);
        } catch (Exception ex){
            throw new Exception(ex);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) throws Exception {
        try {
            Optional<User> user = userService.findById(userId);
            if(user.isPresent()){
                userService.deleteUser(user.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                throw new Exception("Usuário não encontrado!");
            }
        } catch (Exception ex){
            throw new Exception(ex);
        }


    }


}
