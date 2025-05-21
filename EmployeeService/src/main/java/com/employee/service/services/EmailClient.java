package com.employee.service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.service.entities.Email;

@FeignClient(url = "http://localhost:8082", value="Email-Service")
public interface EmailClient {

	@GetMapping("/email/by-employee/{employeeId}")
    Email getEmailByEmployeeId(@PathVariable int employeeId);
}
