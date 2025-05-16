package com.emp.crud.services.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.entities.Employee;
import com.emp.crud.repository.EmployeeRepository;

@Service
public class EmployeeFileReader {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> importFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            List<Employee> emps = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = new Employee();
                emp.setName(data[0]);
                emp.setDepartment(data[1]);
                emp.setEmail(data[2]);
                emp.setSalary(Double.parseDouble(data[3]));
                emps.add(emp);
//                employeeRepository.save(emp);
            }
            return emps;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
}
