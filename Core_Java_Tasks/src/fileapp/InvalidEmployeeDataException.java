package fileapp;

public class InvalidEmployeeDataException extends Exception {

	public InvalidEmployeeDataException() {
		super();
	}

	public InvalidEmployeeDataException(String message) {
		super(message);
	}

}
