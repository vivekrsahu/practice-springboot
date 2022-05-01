package practice.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import practice.springboot.rest.model.Employee;
import practice.springboot.rest.service.EmployeeService;

@RestController(value = "/api/v1/employee")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping(value = "/ping")
	public String checkHealth() {
		return "Success";
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public List<Employee> getAll() {
		return service.getAll();
	}

	@GetMapping(value = "/getById/{id}", produces = "application/json")
	public Employee getById(@PathVariable Long id) {
		return service.getById(id);
	}

}
