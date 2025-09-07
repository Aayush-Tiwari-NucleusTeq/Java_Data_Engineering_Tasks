package com.email.service.controller;

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

import com.email.service.entities.Email;
import com.email.service.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
    private EmailService emailService;

	@PostMapping
    public ResponseEntity<Email> createEmail(@RequestBody Email email) {
        Email createdEmail = emailService.saveEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmail);
    }

    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails() {
        List<Email> emails = emailService.getAllEmails();
        return ResponseEntity.ok(emails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable int id) {
        return emailService.getEmailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/by-employee/{employeeId}")
    public ResponseEntity<Email> getEmailByEmployeeId(@PathVariable int employeeId) {
        return emailService.getEmailByEmployeeId(employeeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable int id, @RequestBody Email email) {
        Email updatedEmail = emailService.updateEmail(id, email);
        return ResponseEntity.ok(updatedEmail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable int id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}
