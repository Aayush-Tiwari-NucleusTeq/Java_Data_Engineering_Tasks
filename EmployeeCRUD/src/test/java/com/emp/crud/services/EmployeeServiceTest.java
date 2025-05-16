package com.emp.crud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.emp.crud.dto.in.EmployeeInDTO;
import com.emp.crud.dto.out.EmployeeOutDTO;
import com.emp.crud.entities.Employee;
import com.emp.crud.repository.EmployeeRepository;
import com.emp.crud.services.impl.EmployeeServiceImpl;

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
	void testUpdateEmployee() {
	    Employee existing = new Employee(1, "X", "Ops", "x@gmail.com", 3000.0);
	    Employee updated = new Employee(1, "Y", "Tech", "x@gmail.com", 4000.0);
	    EmployeeInDTO updateDto = new EmployeeInDTO("Y", "Tech", "x@gmail.com", 4000.0);

	    when(employeeRepository.findById(1)).thenReturn(Optional.of(existing));
	    when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

	    EmployeeOutDTO result = employeeService.updateEmployee(1, updateDto);

	    assertEquals("Y", result.getName());
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
