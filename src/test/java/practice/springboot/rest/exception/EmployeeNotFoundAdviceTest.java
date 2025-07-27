package practice.springboot.rest.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeNotFoundAdviceTest {

    @Test
    void testEmployeeNotFoundHandler() {
        EmployeeNotFoundAdvice advice = new EmployeeNotFoundAdvice();
        String message = advice.employeeNotFoundHandler(new EmployeeNotFoundException(42L));
        assertEquals("Could not find employee with ID: 42", message);
    }
}
