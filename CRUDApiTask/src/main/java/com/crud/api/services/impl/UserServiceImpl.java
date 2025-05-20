package com.crud.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.api.controller.repository.UserRepository;
import com.crud.api.dto.in.UserInDto;
import com.crud.api.dto.out.UserOutDto;
import com.crud.api.entities.User;
import com.crud.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserOutDto createUser(UserInDto userInDTO) {
		User user = userInDtoToUser(userInDTO);
		User savedUser = userRepository.save(user);
		return userToUserOutDto(savedUser);
	}

	@Override
	public UserOutDto getUserById(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		return userToUserOutDto(user);
	}

	@Override
	public List<UserOutDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::userToUserOutDto).collect(Collectors.toList());
	}

	@Override
	public UserOutDto updateUser(int userId, String name) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		user.setName(name);
		User updatedUser = userRepository.save(user);
		return userToUserOutDto(updatedUser);
	}

	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		userRepository.delete(user);
	}

	private User userInDtoToUser(UserInDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		return user;
	}

	private UserOutDto userToUserOutDto(User user) {
		UserOutDto dto = new UserOutDto();
		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}

}
