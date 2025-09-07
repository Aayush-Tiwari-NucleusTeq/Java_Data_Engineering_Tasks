package com.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.service.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
