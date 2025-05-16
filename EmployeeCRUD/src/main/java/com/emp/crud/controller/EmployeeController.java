package com.emp.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.crud.entities.Employee;
import com.emp.crud.services.impl.EmployeeFileReader;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private EmployeeFileReader employeeFileReader;

	@GetMapping
	public String index() {
		return "The service is running fine";
	}
	
	@PostMapping("/import")
    public ResponseEntity<?> importEmployees() {
		List<Employee> employees = employeeFileReader.importFromCSV("src/main/resources/employees.csv");
        return ResponseEntity.ok(employees);
    }

}
