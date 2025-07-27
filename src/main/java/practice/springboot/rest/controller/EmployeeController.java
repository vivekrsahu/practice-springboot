package practice.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.springboot.rest.entity.Employee;
import practice.springboot.rest.service.EmployeeService;
import practice.springboot.rest.exception.EmployeeNotFoundException;

import static practice.springboot.rest.constants.APIConstants.*;

/**
 * To expose REST APIs for Employee related data.
 */
@RestController
@RequestMapping(ROOT_PATH_EMPLOYEE)
public class EmployeeController {

	private final EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	/**
	 * Adds a new employee, it accepts an employee object in JSON format.
	 *
	 * @param emp employee JSON object
	 */
	@PostMapping(value = EMPLOYEE_ADD, consumes = APPLICATION_JSON)
	public void addEmployee(@RequestBody Employee emp) {
		service.addEmployee(emp);
	}

	/**
	 * Retrieves all employees present in the system. It returns with hypermedia links for navigation.
	 *
	 * @return a collection of {@link EntityModel} containing all employee resources
	 */
	@GetMapping(value = EMPLOYEE_ALL, produces = APPLICATION_JSON)
	public CollectionModel<EntityModel<Employee>> getAll() {
		return service.getAll();
	}

	/**
	 * Retrieves a single employee as per the provided id.
	 *
	 * @param id employee id to be retrieved
	 * @return an {@link EntityModel} containing the employee data
	 * @throws EmployeeNotFoundException {@link EmployeeNotFoundException} if the employee is not found
	 */
	@GetMapping(value = EMPLOYEE_BY_ID, produces = APPLICATION_JSON)
	public EntityModel<Employee> getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/**
	 * Deletes an employee as per the provided id.
	 *
	 * @param id employee id to be deleted
	 */
	@DeleteMapping(value = EMPLOYEE_BY_ID)
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}

	/**
	 * Deletes all employees present in the system.
	 */
	@DeleteMapping(value = EMPLOYEE_ALL)
	public void deleteAll() {
		service.deleteAll();
	}

}
