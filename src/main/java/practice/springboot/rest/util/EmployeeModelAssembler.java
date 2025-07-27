package practice.springboot.rest.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import practice.springboot.rest.controller.EmployeeController;
import practice.springboot.rest.entity.Employee;

/**
 * It converts Employee domain objects into HATEOAS-compliant {@link EntityModel} representation.
 * It wraps the Employee data and adds relevant hypermedia links for REST API navigation.
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	/**
	 * It provides an EntityModel object of Employee domain with a self-referential link
	 * and a link to the main employee resource.
	 *
	 * @param employee non-null employee domain object
	 * @return converted EntityModel object
	 */
	@Override
	public @NonNull EntityModel<Employee> toModel(@NonNull Employee employee) {
		return EntityModel.of(employee,
				linkTo(methodOn(EmployeeController.class).getById(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"));
	}

}
