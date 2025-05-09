package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LegacyIO implements IReadWriteFunctions {

	@Override
	public List<Employee> readEmployees(String filepath){
		List<Employee> employees = new ArrayList<Employee>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                employees.add(Employee.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
	}
	
	@Override
	public void writeEmployee(String filepath, List<Employee> employees){
		File file = new File(filepath);
	    boolean fileExists = file.exists();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			if(!fileExists) {
				writer.write("empId,name,department,salary\n");				
			}
            for (Employee emp : employees) {
                writer.write(emp.toCSV() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
