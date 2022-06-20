package practice.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import practice.springboot.rest.model.Employee;
import practice.springboot.rest.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping(value = "/ping")
	public String checkHealth() {
		return "Success";
	}

	@PostMapping(value = "/api/v1/employee/add", produces = "application/json")
	public void addEmployee(@RequestBody Employee emp) {
		service.addEmployee(emp);
	}

	@GetMapping(value = "/api/v1/employee/getAll", produces = "application/json")
	public CollectionModel<EntityModel<Employee>> getAll() {
		return service.getAll();
	}

	@GetMapping(value = "/api/v1/employee/getById/{id}", produces = "application/json")
	public EntityModel<Employee> getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@DeleteMapping(value = "/api/v1/employee/deleteById/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		service.deleteEmployee(id);
	}

	@DeleteMapping(value = "/api/v1/employee/deleteAll")
	public void deleteAll() {
		service.deleteAll();
	}

}
