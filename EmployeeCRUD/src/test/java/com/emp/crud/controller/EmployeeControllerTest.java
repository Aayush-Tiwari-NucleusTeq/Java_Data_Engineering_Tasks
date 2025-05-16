package com.emp.crud.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.services.EmployeeService;
import com.emp.crud.services.impl.EmployeeFileReader;
import com.emp.crud.services.impl.EmployeeFileWriter;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeFileReader employeeFileReader;

    @Mock
    private EmployeeFileWriter employeeFileWriter;

    @Test
    void testCreateEmployee() {
        EmployeeInDTO inDTO = new EmployeeInDTO("John Doe", "HR", "john@gmail.com", 50000.0);
        EmployeeOutDTO outDTO = new EmployeeOutDTO("John Doe", "HR", "john@gmail.com", 50000.0);

        Mockito.when(employeeService.createEmployee(inDTO)).thenReturn(outDTO);

        ResponseEntity<EmployeeOutDTO> response = employeeController.createEmployee(inDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testGetEmployeeById() {
        int empId = 1;
        EmployeeOutDTO outDTO = new EmployeeOutDTO("Jane Smith", "Finance", "jane@gmail.com", 60000.0);

        Mockito.when(employeeService.getEmployeeById(empId)).thenReturn(outDTO);

        ResponseEntity<EmployeeOutDTO> response = employeeController.getEmployeeById(empId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Jane Smith", response.getBody().getName());
    }

    @Test
    void testGetAllEmployees() {
        List<EmployeeOutDTO> list = List.of(
                new EmployeeOutDTO("Emp1", "Dept1", "emp1@gmail.com", 50000.0),
                new EmployeeOutDTO("Emp2", "Dept2", "emp2@gmail.com", 60000.0)
        );

        Mockito.when(employeeService.getAllEmployees()).thenReturn(list);

        ResponseEntity<List<EmployeeOutDTO>> response = employeeController.getAllEmployees();

        Assertions.assertEquals(2, response.getBody().size());
    }

    @Test
    void testUpdateEmployee() {
        int empId = 2;
        EmployeeInDTO inDTO = new EmployeeInDTO("Updated", "IT", "updated@gmail.com", 75000.0);
        EmployeeOutDTO outDTO = new EmployeeOutDTO("Updated", "IT", "updated@gmail.com", 75000.0);

        Mockito.when(employeeService.updateEmployee(empId, inDTO)).thenReturn(outDTO);

        ResponseEntity<EmployeeOutDTO> response = employeeController.updateEmployee(empId, inDTO);

        Assertions.assertEquals("Updated", response.getBody().getName());
    }

    @Test
    void testDeleteEmployee() {
        String email = "delete@gmail.com";

        ResponseEntity<String> response = employeeController.deleteEmployee(email);

        Mockito.verify(employeeService).deleteEmployee(email);
        Assertions.assertEquals("The employee is deleted successfully", response.getBody());
    }

    @Test
    void testImportEmployees() {
        String fileName = "test.csv";
        List<Employee> employees = List.of(new Employee());

        Mockito.when(employeeFileReader.importFromCSV(Mockito.anyString())).thenReturn(employees);

        ResponseEntity<?> response = employeeController.importEmployees(fileName);

        Assertions.assertEquals(employees, response.getBody());
    }

    @Test
    void testExportEmployees() {
        Mockito.when(employeeFileWriter.writeEmployee(Mockito.anyString()))
                .thenReturn("Exported Successfully");

        ResponseEntity<?> response = employeeController.exportEmployees();

        Assertions.assertEquals("Exported Successfully", response.getBody());
    }
}
