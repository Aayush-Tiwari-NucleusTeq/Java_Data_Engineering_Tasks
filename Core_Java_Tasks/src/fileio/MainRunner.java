package fileio;

import java.util.ArrayList;
import java.util.List;

public class MainRunner {

	public static void main(String[] args) {
		System.out.println("Start of the program");
		String inputPath = "J:\\Java_Data_Engineering_Training\\Java_Data_Engineering_Tasks\\Core_Java_Tasks\\src\\fileio\\employee.csv";
		String outputPath = "J:\\Java_Data_Engineering_Training\\Java_Data_Engineering_Tasks\\Core_Java_Tasks\\src\\fileio\\employeeoutput.csv";
		
		// Legacy IO functions
		LegacyIO legacyIO = new LegacyIO();
		List<Employee> employees = legacyIO.readEmployees(inputPath);
		for(Employee emp: employees) {
			System.out.println(emp);
		}
		
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(500, "David", "Engineering", "1000"));
		legacyIO.writeEmployee(outputPath, empList);
		
		// NIO functions
		System.out.println();
		NIO nio = new NIO();
		List<Employee> empList2 = nio.readEmployees(inputPath);
		for(Employee emp: empList2) {
			System.out.println(emp);
		}
		
		nio.writeEmployee(outputPath, empList);
	}

}
