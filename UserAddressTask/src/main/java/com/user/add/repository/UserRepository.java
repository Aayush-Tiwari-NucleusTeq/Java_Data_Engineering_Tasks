package com.user.add.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.add.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
