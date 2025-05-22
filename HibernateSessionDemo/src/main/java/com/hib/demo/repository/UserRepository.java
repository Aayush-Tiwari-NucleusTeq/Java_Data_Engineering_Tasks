package com.hib.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hib.demo.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
