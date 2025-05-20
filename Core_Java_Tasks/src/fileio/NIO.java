package fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NIO implements IReadWriteFunctions{

	@Override
	public List<Employee> readEmployees(String filepath) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get(filepath));
			return allLines.stream()
					.skip(1)
					.map(Employee::fromCSV)
					.filter(emp -> Integer.parseInt(emp.getSalary()) > 50000)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return List.of();
	}

	@Override
	public void writeEmployee(String filepath, List<Employee> employees) {
		List<String> linesToWrite = new ArrayList<String>();
		
		Path path = Paths.get(filepath);
		boolean fileExists = Files.exists(path);
		if(!fileExists) {
			linesToWrite.add("id,name,department,salary");			
		}

        for (Employee emp : employees) {
            linesToWrite.add(emp.toCSV());
        }

        try {
            Files.write(Paths.get(filepath), linesToWrite, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
