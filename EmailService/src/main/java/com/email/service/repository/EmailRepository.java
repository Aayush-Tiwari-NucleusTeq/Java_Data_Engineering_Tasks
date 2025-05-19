package com.email.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.service.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {

	Optional<Email> findByEmployeeId(int employeeId);
}
