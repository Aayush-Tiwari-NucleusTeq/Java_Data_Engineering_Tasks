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

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeFileReader employeeFileReader;
	
	@Autowired
	private EmployeeFileWriter employeeFileWriter;
	
	@Autowired
    private EmployeeService employeeService;
	
	@PostMapping("/import")
    public ResponseEntity<?> importEmployees(@RequestParam("fileName") String fileName) {
		List<Employee> employees = employeeFileReader.importFromCSV("src\\main\\resources\\files\\" + fileName);
        return ResponseEntity.ok(employees);
    }
	
	@PostMapping("/export")
    public ResponseEntity<?> exportEmployees() {
		String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String saved = this.employeeFileWriter.writeEmployee("src\\main\\resources\\files\\employee" + substring + ".csv");
        return ResponseEntity.ok(saved);
    }
	
    @PostMapping
    public ResponseEntity<EmployeeOutDTO> createEmployee(@Valid @RequestBody EmployeeInDTO employeeInDTO) {
        EmployeeOutDTO createdEmployee = employeeService.createEmployee(employeeInDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeOutDTO> getEmployeeById(@PathVariable int id) {
        EmployeeOutDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeOutDTO>> getAllEmployees() {
        List<EmployeeOutDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeOutDTO> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeInDTO employeeInDTO) {
        EmployeeOutDTO updatedEmployee = employeeService.updateEmployee(id, employeeInDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
