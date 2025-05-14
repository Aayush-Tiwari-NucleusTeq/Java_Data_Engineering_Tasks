package fileio;

import java.util.List;

public interface IReadWriteFunctions {

	List<Employee> readEmployees(String filepath);
	void writeEmployee(String filepath, List<Employee> employees);
}
