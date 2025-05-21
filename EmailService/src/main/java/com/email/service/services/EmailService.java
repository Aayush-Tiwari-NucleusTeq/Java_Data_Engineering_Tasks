package com.email.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.service.entities.Email;
import com.email.service.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
    private EmailRepository emailRepository;

    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    public Optional<Email> getEmailById(int id) {
        return emailRepository.findById(id);
    }

    public Optional<Email> getEmailByEmployeeId(int employeeId) {
        return emailRepository.findByEmployeeId(employeeId);
    }

    public Email updateEmail(int id, Email updatedEmail) {
        Email email = emailRepository.findById(id).orElseThrow(() -> new RuntimeException("Email not found"));
        email.setEmployeeId(updatedEmail.getEmployeeId());
        email.setEmail(updatedEmail.getEmail());
        return emailRepository.save(email);
    }

    public void deleteEmail(int id) {
        emailRepository.deleteById(id);
    }
}
