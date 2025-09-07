package com.emp.crud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.repository.EmployeeRepository;
import com.emp.crud.services.impl.EmployeeServiceImpl;
import com.emp.crud.utils.UpdateRequest;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	 @Mock
	 private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
	
	@Test
	void testCreateEmployee() {
	    EmployeeInDTO inDto = new EmployeeInDTO("John", "IT", "john@gmail.com", 50000.0);
	    Employee employee = new Employee(1, "John", "IT", "john@gmail.com", 50000.0);

	    when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

	    EmployeeOutDTO result = employeeService.createEmployee(inDto);

	    assertEquals("John", result.getName());
	}
	
	@Test
	void testGetEmployeeById() {
	    Employee employee = new Employee(1, "Alice", "HR", "alice@gmail.com", 60000.0);
	    when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

	    EmployeeOutDTO result = employeeService.getEmployeeById(1);

	    assertEquals("Alice", result.getName());
	}
	
	@Test
	void testGetAllEmployees() {
	    List<Employee> list = List.of(
	        new Employee(1, "A", "IT", "a@gmail.com", 1000.0),
	        new Employee(2, "B", "HR", "b@gmail.com", 2000.0)
	    );
	    when(employeeRepository.findAll()).thenReturn(list);

	    List<EmployeeOutDTO> result = employeeService.getAllEmployees();

	    assertEquals(2, result.size());
	}
	
	@Test
    void testUpdateEmployee_PartialFields() {
        // Given
        String email = "john.doe@gmail.com";
        Employee existingEmployee = new Employee(1, "John", "Sales", email, 50000.0);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setName("Johnny"); // Only name is updated
        updateRequest.setDepartment(null); // Should remain unchanged
        updateRequest.setSalary(null);     // Should remain unchanged

        Employee updatedEmployee = new Employee(1, "Johnny", "Sales", email, 50000.0);

        Mockito.when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(existingEmployee));
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        // When
        EmployeeOutDTO result = employeeService.updateEmployee(email, updateRequest);

        // Then
        Assertions.assertEquals("Johnny", result.getName());
        Assertions.assertEquals("Sales", result.getDepartment());
        Assertions.assertEquals(email, result.getEmail());
        Assertions.assertEquals(50000.0, result.getSalary());
    }
	
	@Test
	void testDeleteEmployee() {
	    Employee employee = new Employee(1, "Z", "IT", "z@gmail.com", 7000.0);
	    when(employeeRepository.findByEmail("z@gmail.com")).thenReturn(Optional.of(employee));

	    employeeService.deleteEmployee("z@gmail.com");

	    verify(employeeRepository, times(1)).deleteById(1);
	}
	
	@Test
	void testGetEmployeeByEmail() {
	    Employee employee = new Employee(1, "Tom", "Admin", "tom@gmail.com", 8000.0);
	    when(employeeRepository.findByEmail("tom@gmail.com")).thenReturn(Optional.of(employee));

	    EmployeeOutDTO result = employeeService.getEmployeeByEmail("tom@gmail.com");

	    assertEquals("Tom", result.getName());
	}
	
	
}
