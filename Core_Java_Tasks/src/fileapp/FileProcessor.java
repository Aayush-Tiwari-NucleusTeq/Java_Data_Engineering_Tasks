package fileapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
	
	 public List<Employee> readEmployees(String filepath) throws InvalidEmployeeDataException {
	        List<Employee> employees = new ArrayList<Employee>();
	        BufferedReader reader = null;

	        try {
	            reader = new BufferedReader(new FileReader(filepath));
	            String line;
	            reader.readLine();

	            while ((line = reader.readLine()) != null) {
	            	try {
	            		Employee emp = Employee.fromCSV(line);
	            		employees.add(emp);	            		
	            	} catch(InvalidEmployeeDataException e) {
	            		System.out.println(e.getMessage());
	            	}
	            }

	        } catch (FileNotFoundException e) {
	            System.out.println("File not found: " + filepath);
	        } catch (IOException e) {
	            System.out.println("I/O error: " + e.getMessage());
	        } finally {
	            try {
	                if (reader != null) reader.close();
	            } catch (IOException e) {
	                System.out.println("Error closing file");
	            }
	        }

	        return employees;
	    }

}
