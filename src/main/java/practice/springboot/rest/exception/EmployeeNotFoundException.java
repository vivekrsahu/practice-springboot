package practice.springboot.rest.exception;

import java.io.Serial;

/**
 * It is thrown to indicate that an employee is not found in the system.
 */
public class EmployeeNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a parameterized EmployeeNotFoundException with a detail message.
	 *
	 * @param id associated employee id
	 */
	public EmployeeNotFoundException(Long id) {
		super("Could not find employee with ID: " + id);
	}

}
