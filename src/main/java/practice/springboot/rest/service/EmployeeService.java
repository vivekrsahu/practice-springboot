package practice.springboot.rest.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import practice.springboot.rest.controller.EmployeeController;
import practice.springboot.rest.exception.EmployeeNotFoundException;
import practice.springboot.rest.entity.Employee;
import practice.springboot.rest.repository.EmployeeRepository;
import practice.springboot.rest.util.EmployeeModelAssembler;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EmployeeModelAssembler assembler;

	public void addEmployee(Employee emp) {
		repository.save(emp);
	}

	public CollectionModel<EntityModel<Employee>> getAll() {
		List<EntityModel<Employee>> employees = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());
	}

	public EntityModel<Employee> getById(Long id) {
		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return assembler.toModel(employee);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
