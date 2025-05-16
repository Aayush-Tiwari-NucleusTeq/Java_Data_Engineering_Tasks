package com.emp.crud.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeOutDTO {

	private int empId;
    private String name;
    private String department;
    private String email;
    private Double salary;
}
