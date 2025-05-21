package com.emp.crud.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.exception.ResourceNotFoundException;
import com.emp.crud.repository.EmployeeRepository;
import com.emp.crud.services.EmployeeService;
import com.emp.crud.utils.UpdateRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Converts Employee entity to EmployeeOutDTO.
     */
    private EmployeeOutDTO employeeToEmployeeOutDTO(Employee employee) {
        EmployeeOutDTO dto = new EmployeeOutDTO();
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    /**
     * Converts EmployeeInDTO to Employee entity.
     */
    private Employee employeeInDtoToEmployee(EmployeeInDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    /**
     * Creates a new employee.
     *
     * @param employeeInDTO Input DTO with employee data.
     * @return EmployeeOutDTO with saved employee data.
     */
    @Override
    public EmployeeOutDTO createEmployee(EmployeeInDTO employeeInDTO) {
        log.info("Creating new employee with email: {}", employeeInDTO.getEmail());
        Employee employee = employeeInDtoToEmployee(employeeInDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created with ID: {}", savedEmployee.getEmpId());
        return employeeToEmployeeOutDTO(savedEmployee);
    }

    /**
     * Fetches an employee by ID.
     *
     * @param empId Employee ID.
     * @return EmployeeOutDTO of the found employee.
     */
    @Override
    public EmployeeOutDTO getEmployeeById(int empId) {
        log.info("Fetching employee with ID: {}", empId);
        Employee employee = employeeRepository.findById(empId)
            .orElseThrow(() -> {
                log.error("Employee not found with ID: {}", empId);
                return new ResourceNotFoundException("Employee not found with id: " + empId);
            });
        return employeeToEmployeeOutDTO(employee);
    }

    /**
     * Retrieves all employees.
     *
     * @return List of EmployeeOutDTO.
     */
    @Override
    public List<EmployeeOutDTO> getAllEmployees() {
        log.info("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::employeeToEmployeeOutDTO).collect(Collectors.toList());
    }

    /**
     * Updates an existing employee.
     *
     * @param email Employee email.
     * @param employeeInDTO Updated data.
     * @return Updated EmployeeOutDTO.
     */
    @Override
    public EmployeeOutDTO updateEmployee(String email, UpdateRequest employeeInDTO) {
        log.info("Updating employee with email: {}", email);
        Employee existingEmployee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Employee not found with ID: {}", email);
                return new ResourceNotFoundException("Employee not found with email: " + email);
            });
        existingEmployee.setName(employeeInDTO.getName() != null ? employeeInDTO.getName() : existingEmployee.getName());
        existingEmployee.setDepartment(employeeInDTO.getDepartment() != null ? employeeInDTO.getDepartment() : existingEmployee.getDepartment());
        existingEmployee.setSalary(employeeInDTO.getSalary() != null ? employeeInDTO.getSalary() : existingEmployee.getSalary());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        log.info("Employee updated successfully with email: {}", email);
        return employeeToEmployeeOutDTO(updatedEmployee);
    }

    /**
     * Deletes an employee by email.
     *
     * @param email Employee's email.
     */
    @Override
    public void deleteEmployee(String email) {
        log.info("Deleting employee with email: {}", email);
        Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Employee not found with email: {}", email);
                return new ResourceNotFoundException("Employee not found with email: " + email);
            });
        employeeRepository.deleteById(employee.getEmpId());
        log.info("Employee deleted with ID: {}", employee.getEmpId());
    }

    /**
     * Fetches an employee by email.
     *
     * @param email Email address.
     * @return EmployeeOutDTO of the found employee.
     */
    @Override
    public EmployeeOutDTO getEmployeeByEmail(String email) {
        log.info("Fetching employee with email: {}", email);
        Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Employee not found with email: {}", email);
                return new ResourceNotFoundException("Employee not found with email: " + email);
            });
        return employeeToEmployeeOutDTO(employee);
    }
}
