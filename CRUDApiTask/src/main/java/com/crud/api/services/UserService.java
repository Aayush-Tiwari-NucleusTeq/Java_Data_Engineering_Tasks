package com.crud.api.services;

import java.util.List;

import com.crud.api.dto.in.UserInDto;
import com.crud.api.dto.out.UserOutDto;

public interface UserService {

    UserOutDto createUser(UserInDto userInDTO);
    UserOutDto getUserById(int userId);
    List<UserOutDto> getAllUsers();
    UserOutDto updateUser(int userId, String name);
    void deleteUser(int userId);
}
