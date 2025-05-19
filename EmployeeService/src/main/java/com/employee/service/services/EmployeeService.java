package com.employee.service.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.service.entities.Email;
import com.employee.service.entities.Employee;
import com.employee.service.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmailClient emailClient;

	@Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmp) {
        Optional<Employee> existingEmp = employeeRepository.findById(id);
        if (existingEmp.isPresent()) {
            Employee emp = existingEmp.get();
            emp.setName(updatedEmp.getName());
            return employeeRepository.save(emp);
        } else {
            return null;
        }
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
    
    public Map<String, Object> getEmployeeWithEmail(int id) {
    	System.out.println("Under the service box");
        Employee emp = new Employee(id, "John Doe");
        Email email = emailClient.getEmailByEmployeeId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("employee", emp);
        response.put("email", email);
        return response;
    }
}
