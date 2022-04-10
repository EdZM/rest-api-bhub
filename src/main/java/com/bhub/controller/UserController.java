package com.bhub.controller;

import com.bhub.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/all")
    public ResponseEntity<HttpStatus> listUsers(){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<HttpStatus> findById(@PathVariable Long userId){
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userdto){
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<HttpStatus> createUser(@PathVariable Long userId, @RequestBody UserDto userdto){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<HttpStatus> createUser(@PathVariable Long userId){
        return ResponseEntity.ok().build();
    }


}
