package com.emp.crud.dto.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for receiving employee input details.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInDTO {

    /**
     * Name of the employee.
     * Must not be blank and should only contain letters and spaces.
     */
    @NotBlank(message = "Name is mandatory")
    @Pattern(
        regexp = "^[A-Za-z ]+$",
        message = "Name must contain only letters and spaces"
    )
    private String name;

    /**
     * Department to which the employee belongs.
     */
    private String department;

    /**
     * Email of the employee.
     * Must be a valid Gmail address and cannot be blank.
     */
    @NotBlank(message = "Email is mandatory")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$",
        message = "Email format is invalid"
    )
    private String email;

    /**
     * Salary of the employee.
     */
    private Double salary;
}