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

/**
 * Contains business logic for all employee-related operations.
 * <br>It uses {@link EmployeeModelAssembler} to convert entities to REST models.
 */
@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	private final EmployeeModelAssembler assembler;

	@Autowired
	public EmployeeService(EmployeeRepository repository, EmployeeModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	/**
	 * Saves a new employee to the database.
	 *
	 * @param emp the employee object to be added
	 */
	public void addEmployee(Employee emp) {
		if (emp != null) {
			repository.save(emp);
		}
	}

	/**
	 * Fetches all employees from the database and converts the result to a collection of EntityModel,
	 * that includes a self-referencing link.
	 *
	 * @return {@link CollectionModel} containing a list of {@link EntityModel} of type {@link Employee}
	 */
	public CollectionModel<EntityModel<Employee>> getAll() {
		List<EntityModel<Employee>> employees = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());
	}

	/**
	 * Fetches a single employee from the database as per the provided id.
	 * <br>It wraps the employee data to an {@link EntityModel}.
	 *
	 * @param id employee id
	 * @return {@link EntityModel} containing employee data
	 * @throws EmployeeNotFoundException {@link EmployeeNotFoundException} if the employee is not found
	 */
	public EntityModel<Employee> getById(Long id) {
		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return assembler.toModel(employee);
	}

	/**
	 * Deletes an employee from the database as per the provided id.
	 *
	 * @param id employee id
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Deletes all employees from the database.
	 */
	public void deleteAll() {
		repository.deleteAll();
	}

}
