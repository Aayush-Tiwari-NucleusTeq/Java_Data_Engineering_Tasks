package com.hib.demo.service;

import org.springframework.stereotype.Service;

import com.hib.demo.entities.User;
import com.hib.demo.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class MyService {

    private final UserRepository repo;
    private final EntityManager em;

    public MyService(UserRepository repo, EntityManager em) {
        this.repo = repo;
        this.em = em;
    }

    @Transactional
    public void testFlush() {
        // Create user
        User user = new User();
        user.setUserId("u123");
        user.setName("Alice");
        user.setEmail("alice@example.com");
        repo.save(user);

        // Modify user
        User fetched = repo.findById("u123").get();
        fetched.setEmail("alice.new@example.com");

        // Flush forces Hibernate to sync changes to DB
        em.flush();
    }
}