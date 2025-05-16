package com.emp.crud.dto.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInDTO {
	
	@NotBlank(message = "Name is mandatory")
	@Pattern(
		    regexp = "^[A-Za-z ]+$",
		    message = "Name must contain only letters and spaces"
		)
	private String name;
    private String department;
    @NotBlank(message = "Email is mandatory")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$",
        message = "Email format is invalid"
    )
    private String email;
    private Double salary;
}
