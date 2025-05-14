package fileapp;

import java.util.List;

public class Main {

	public static void main(String[] args) throws InvalidEmployeeDataException {
		
		System.out.println("Start of the program \n");
		FileProcessor fileProcessor = new FileProcessor();
		String inputPath = "J:\\Java_Data_Engineering_Training\\Java_Data_Engineering_Tasks\\Core_Java_Tasks\\src\\fileapp\\employee.csv";
			List<Employee> employees = fileProcessor.readEmployees(inputPath);
			for(Employee emp : employees) {
				System.out.println(emp);
			}																								
	}

}
