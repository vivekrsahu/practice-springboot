package practice.springboot.rest.exception;

import java.io.Serial;

public class EmployeeNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
		super("Could not find employee with ID: " + id);
	}

}
