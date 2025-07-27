package practice.springboot.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import practice.springboot.rest.entity.Employee;
import practice.springboot.rest.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    private Employee sampleEmp;

    @BeforeEach
    void setup() {
        sampleEmp = new Employee("John", "Developer");
        sampleEmp.setId(1L);
    }

    @Test
    void testAddEmployee() {
        controller.addEmployee(sampleEmp);
        verify(service).addEmployee(sampleEmp);
    }

    @Test
    void testGetById() {
        EntityModel<Employee> model = EntityModel.of(sampleEmp);
        when(service.getById(1L)).thenReturn(model);

        EntityModel<Employee> result = controller.getById(1L);
        assertNotNull(result.getContent());
        assertEquals("John", result.getContent().getName());
        verify(service).getById(1L);
    }

    @Test
    void testGetAll() {
        List<EntityModel<Employee>> models = List.of(EntityModel.of(sampleEmp));
        when(service.getAll()).thenReturn(CollectionModel.of(models));

        CollectionModel<EntityModel<Employee>> result = controller.getAll();
        assertEquals(1, result.getContent().size());
        verify(service).getAll();
    }

    @Test
    void testDeleteById() {
        controller.deleteById(1L);
        verify(service).deleteById(1L);
    }

    @Test
    void testDeleteAll() {
        controller.deleteAll();
        verify(service).deleteAll();
    }

}
