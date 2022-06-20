package practice.springboot.rest.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import practice.springboot.rest.controller.EmployeeController;
import practice.springboot.rest.model.Employee;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		return EntityModel.of(employee,
				linkTo(methodOn(EmployeeController.class).getById(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"));
	}

}
