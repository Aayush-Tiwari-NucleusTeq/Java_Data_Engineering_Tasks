package com.emp.crud.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.entities.Employee;
import com.emp.crud.repository.EmployeeRepository;

@Service
public class EmployeeFileWriter {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public String writeEmployee(String filepath) {
		List<Employee> employees = this.employeeRepository.findAll();
		List<String> linesToWrite = new ArrayList<>();
		
		Path path = Paths.get(filepath);
		boolean fileExists = Files.exists(path);
		if(!fileExists) {
			linesToWrite.add("name,department,email,salary");			
		}

        for (Employee emp : employees) {
            linesToWrite.add(emp.toCSV());
        }

        try {
            Files.write(Paths.get(filepath), linesToWrite, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return "File is successfully saved";
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "Something went wrong";
	}
}
