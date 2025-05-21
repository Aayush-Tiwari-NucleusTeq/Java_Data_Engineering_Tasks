package com.emp.crud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing the Employee table in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    /**
     * Unique identifier for the employee.
     * This is the primary key and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    /**
     * Name of the employee.
     */
    private String name;

    /**
     * Department where the employee works.
     */
    private String department;

    /**
     * Unique email address of the employee.
     * Cannot be null and must be unique.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Salary of the employee.
     */
    private Double salary;

    /**
     * Converts the employee details into a CSV string format.
     *
     * @return a CSV-formatted string representing the employee
     */
    public String toCSV() {
        return name + "," + department + "," + email + "," + salary;
    }
}
