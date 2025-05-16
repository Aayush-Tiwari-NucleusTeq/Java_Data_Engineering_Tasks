package com.emp.crud.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInDTO {
	
	private String name;
    private String department;
    private String email;
    private Double salary;
}
