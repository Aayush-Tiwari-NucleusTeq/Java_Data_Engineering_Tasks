package com.emp.crud.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.services.EmployeeService;
import com.emp.crud.services.impl.EmployeeFileReader;
import com.emp.crud.services.impl.EmployeeFileWriter;
import com.emp.crud.utils.UpdateRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeFileReader employeeFileReader;
	
	@Autowired
	private EmployeeFileWriter employeeFileWriter;
	
	@Autowired
    private EmployeeService employeeService;
	
	/**
     * Imports employee data from a CSV file.
     *
     * @param fileName Name of the CSV file to import from.
     * @return List of imported employees.
     */
    @PostMapping("/import")
    public ResponseEntity<?> importEmployees(@RequestParam("fileName") String fileName) {
        log.info("Importing employees from file: {}", fileName);
        List<Employee> employees = employeeFileReader.importFromCSV("src\\main\\resources\\files\\" + fileName);
        log.info("Successfully imported {} employees", employees.size());
        return ResponseEntity.ok(employees);
    }

    /**
     * Exports employee data to a CSV file with a random name.
     *
     * @return Message confirming file creation.
     */
    @PostMapping("/export")
    public ResponseEntity<?> exportEmployees() {
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        String filename = "employee" + substring + ".csv";
        log.info("Exporting employees to file: {}", filename);
        String saved = this.employeeFileWriter.writeEmployee("src\\main\\resources\\files\\" + filename);
        return ResponseEntity.ok(saved);
    }

    /**
     * Creates a new employee.
     *
     * @param employeeInDTO Input data for the employee.
     * @return Created employee data.
     */
    @PostMapping
    public ResponseEntity<EmployeeOutDTO> createEmployee(@Valid @RequestBody EmployeeInDTO employeeInDTO) {
        log.info("Creating employee with email: {}", employeeInDTO.getEmail());
        EmployeeOutDTO createdEmployee = employeeService.createEmployee(employeeInDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id Employee ID.
     * @return Employee details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeOutDTO> getEmployeeById(@PathVariable int id) {
        log.info("Fetching employee with ID: {}", id);
        EmployeeOutDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    /**
     * Retrieves an employee by email.
     *
     * @param email Employee email.
     * @return Employee details.
     */
    @GetMapping("/email")
    public ResponseEntity<EmployeeOutDTO> getEmployeeByEmail(@RequestParam String email) {
        log.info("Fetching employee with email: {}", email);
        EmployeeOutDTO employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }

    /**
     * Retrieves all employees.
     *
     * @return List of all employees.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeOutDTO>> getAllEmployees() {
        log.info("Fetching all employees");
        List<EmployeeOutDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Updates an existing employee.
     *
     * @param id Employee email.
     * @param employeeInDTO Updated employee data.
     * @return Updated employee details.
     */
    @PutMapping("/{email}")
    public ResponseEntity<EmployeeOutDTO> updateEmployee(
            @PathVariable String email,
            @RequestBody UpdateRequest employee) {
        log.info("Updating employee with email: {}", email);
        EmployeeOutDTO updatedEmployee = employeeService.updateEmployee(email, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by email.
     *
     * @param email Employee email.
     * @return Success message.
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String email) {
        log.info("Deleting employee with email: {}", email);
        employeeService.deleteEmployee(email);
        return ResponseEntity.ok("The employee is deleted successfully");
    }

}
