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
	
	
	
}
