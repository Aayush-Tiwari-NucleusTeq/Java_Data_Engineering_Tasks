package com.emp.crud.services;

import java.util.List;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.utils.UpdateRequest;

public interface EmployeeService {

	EmployeeOutDTO createEmployee(EmployeeInDTO employeeInDTO);
    EmployeeOutDTO getEmployeeById(int empId);
    EmployeeOutDTO getEmployeeByEmail(String email);
    List<EmployeeOutDTO> getAllEmployees();
    EmployeeOutDTO updateEmployee(String email, UpdateRequest employee);
    void deleteEmployee(String email);
}
