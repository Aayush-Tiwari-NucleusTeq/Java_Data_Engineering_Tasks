package com.employee.service.controller;

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

import com.employee.service.entities.Email;
import com.employee.service.entities.Employee;
import com.employee.service.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
		Employee updated = employeeService.updateEmployee(id, updatedEmployee);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/email/{id}")
	public ResponseEntity<?> getEmployeeWithMetaData(@PathVariable int id) {
		return ResponseEntity.ok(employeeService.getEmployeeWithEmail(id));
	}
	
	@GetMapping("/email/webClient/{employeeId}")
    public ResponseEntity<Email> getEmailViaWebClient(@PathVariable int employeeId) {
        Email email = employeeService.getEmailwithEmailWebClient(employeeId);
        return ResponseEntity.ok(email);
    }
	
	@GetMapping("/email/resttemplate/{employeeId}")
    public ResponseEntity<Email> getEmailViaRestTemplate(@PathVariable int employeeId) {
        Email email = employeeService.getEmailByEmployeeId(employeeId);
        return ResponseEntity.ok(email);
    }
}
