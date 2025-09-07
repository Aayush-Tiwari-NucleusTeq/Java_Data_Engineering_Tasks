package com.emp.crud.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for sending employee details in responses.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeOutDTO {

    /**
     * Name of the employee.
     */
    private String name;

    /**
     * Department of the employee.
     */
    private String department;

    /**
     * Email address of the employee.
     */
    private String email;

    /**
     * Salary of the employee.
     */
    private Double salary;
}