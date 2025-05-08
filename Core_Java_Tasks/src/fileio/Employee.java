package fileio;

public class Employee {
	private int empId;
	private String name;
	private String department;
	private String salary;
	
	public Employee() {
	}
	
	public Employee(int empId, String name, String department, String salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String toCSV() {
        return empId + "," + name + "," + department + "," + salary;
    }
	
	public static Employee fromCSV(String line) {
        String[] tokens = line.split(",");
        return new Employee(
            Integer.parseInt(tokens[0].trim()),
            tokens[1].trim(),
            tokens[2].trim(),
            tokens[3].trim()
        );
    }

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", department=" + department + ", salary=" + salary
				+ "]";
	}
	
	
}
