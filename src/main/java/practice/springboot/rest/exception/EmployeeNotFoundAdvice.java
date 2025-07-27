package practice.springboot.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Provides centralized exception handling for all exceptions thrown/used across the application.
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

	/**
	 * Handles the {@link EmployeeNotFoundException}, it sets HTTP response status as 404.
	 *
	 * @param empNotFoundException EmployeeNotFoundException
	 * @return error message from the provided exception
	 */
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String employeeNotFoundHandler(EmployeeNotFoundException empNotFoundException) {
		return empNotFoundException.getMessage();
	}

}
