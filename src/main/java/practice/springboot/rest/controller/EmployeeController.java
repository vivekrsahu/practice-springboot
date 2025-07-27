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

import static practice.springboot.rest.constants.APIConstants.*;

@RestController
@RequestMapping(ROOT_PATH_EMPLOYEE)
public class EmployeeController {

	private final EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping(PING)
	public String checkHealth() {
		return "Success";
	}

	@PostMapping(value = EMPLOYEE_ADD, produces = APPLICATION_JSON)
	public void addEmployee(@RequestBody Employee emp) {
		service.addEmployee(emp);
	}

	@GetMapping(value = EMPLOYEE_ALL, produces = APPLICATION_JSON)
	public CollectionModel<EntityModel<Employee>> getAll() {
		return service.getAll();
	}

	@GetMapping(value = EMPLOYEE_BY_ID, produces = APPLICATION_JSON)
	public EntityModel<Employee> getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@DeleteMapping(value = EMPLOYEE_BY_ID)
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}

	@DeleteMapping(value = EMPLOYEE_ALL)
	public void deleteAll() {
		service.deleteAll();
	}

}
