package com.emp.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.crud.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
