package com.emp.crud.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.exception.ResourceNotFoundException;
import com.emp.crud.repository.EmployeeRepository;
import com.emp.crud.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeOutDTO employeeToEmployeeOutDTO(Employee employee) {
        EmployeeOutDTO dto = new EmployeeOutDTO();
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    private Employee employeeInDtoToEmployee(EmployeeInDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    @Override
    public EmployeeOutDTO createEmployee(EmployeeInDTO employeeInDTO) {
        Employee employee = employeeInDtoToEmployee(employeeInDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeToEmployeeOutDTO(savedEmployee);
    }

    @Override
    public EmployeeOutDTO getEmployeeById(int empId) {
        Employee employee = employeeRepository.findById(empId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + empId));
        return employeeToEmployeeOutDTO(employee);
    }

    @Override
    public List<EmployeeOutDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::employeeToEmployeeOutDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeOutDTO updateEmployee(int empId, EmployeeInDTO employeeInDTO) {
        Employee existingEmployee = employeeRepository.findById(empId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + empId));
        existingEmployee.setName(employeeInDTO.getName());
        existingEmployee.setDepartment(employeeInDTO.getDepartment());
        existingEmployee.setEmail(employeeInDTO.getEmail());
        existingEmployee.setSalary(employeeInDTO.getSalary());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeToEmployeeOutDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }
}
