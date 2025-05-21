package com.user.add.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.add.entities.Address;
import com.user.add.entities.User;
import com.user.add.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        if (user.getAddresses() != null) {
            for (Address address : user.getAddresses()) {
                address.setUser(user);
            }
        }
        return userRepository.save(user);
        
//        User savedUser = userRepository.save(user);
//        if (true) {
//            throw new RuntimeException("Simulated failure to test transaction rollback");
//        }
//        return savedUser;
    }
  
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
