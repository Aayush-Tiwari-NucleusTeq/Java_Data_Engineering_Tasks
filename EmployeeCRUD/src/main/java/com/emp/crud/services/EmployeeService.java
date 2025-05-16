package com.emp.crud.services;

import java.util.List;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;

public interface EmployeeService {

	EmployeeOutDTO createEmployee(EmployeeInDTO employeeInDTO);
    EmployeeOutDTO getEmployeeById(int empId);
    List<EmployeeOutDTO> getAllEmployees();
    EmployeeOutDTO updateEmployee(int empId, EmployeeInDTO employeeInDTO);
    void deleteEmployee(int empId);
}
