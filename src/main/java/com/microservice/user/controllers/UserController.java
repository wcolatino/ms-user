package com.microservice.user.controllers;

import com.microservice.user.dtos.UserRecordDto;
import com.microservice.user.models.User;
import com.microservice.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Só para mostrar outra forma de injetar
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserRecordDto userDto){

        var user = new User();
        BeanUtils.copyProperties(userDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

}
