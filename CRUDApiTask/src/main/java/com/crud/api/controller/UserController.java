package com.crud.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.api.dto.in.UserInDto;
import com.crud.api.dto.out.UserOutDto;
import com.crud.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserService userService;
	
    @PostMapping
    public ResponseEntity<UserOutDto> createUser(@RequestBody UserInDto userInDto) {
        UserOutDto userOutDto = userService.createUser(userInDto);
        return new ResponseEntity<>(userOutDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserOutDto> getUserById(@PathVariable int userId) {
        UserOutDto userOutDto = userService.getUserById(userId);
        return new ResponseEntity<>(userOutDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserOutDto>> getAllUsers() {
        List<UserOutDto> userOutDtos = userService.getAllUsers();
        return new ResponseEntity<>(userOutDtos, HttpStatus.OK);
    }

    @PutMapping("/{userId}/{name}")
    public ResponseEntity<UserOutDto> updateUser(@PathVariable("userId") int userId, @PathVariable("name") String name) {
        UserOutDto userOutDto = userService.updateUser(userId, name);
        return new ResponseEntity<>(userOutDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
