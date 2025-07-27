package practice.springboot.rest.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import practice.springboot.rest.entity.Employee;

public class EmployeeModelAssemblerTest {

    @Test
    void testToModel() {
        Employee emp = new Employee("Emp1", "QA");
        emp.setId(1L);

        EmployeeModelAssembler assembler = new EmployeeModelAssembler();
        EntityModel<Employee> model = assembler.toModel(emp);

        assertNotNull(model.getContent());
        assertEquals("Emp1", model.getContent().getName());
        assertTrue(model.getLinks().hasLink("self"));
        assertTrue(model.getLinks().hasLink("employees"));
    }

}
