package practice.springboot.rest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import practice.springboot.rest.entity.Employee;
import practice.springboot.rest.exception.EmployeeNotFoundException;
import practice.springboot.rest.repository.EmployeeRepository;
import practice.springboot.rest.util.EmployeeModelAssembler;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeModelAssembler assembler;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testAddEmployee() {
        Employee emp = new Employee("John", "Developer");
        service.addEmployee(emp);
        verify(repository).save(emp);
    }

    @Test
    void testAddEmployee_NullInput() {
        service.addEmployee(null);
        verify(repository, never()).save(any());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = List.of(new Employee("A", "Dev"), new Employee("B", "QA"));
        when(repository.findAll()).thenReturn(employees);
        when(assembler.toModel(any())).thenAnswer(invocation -> EntityModel.of(invocation.getArgument(0)));

        CollectionModel<EntityModel<Employee>> result = service.getAll();
        assertEquals(2, result.getContent().size());
    }

    @Test
    void testGetByIdFound() {
        Employee emp = new Employee("X", "Test");
        emp.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(emp));
        when(assembler.toModel(emp)).thenReturn(EntityModel.of(emp));

        EntityModel<Employee> result = service.getById(1L);
        assertNotNull(result.getContent());
        assertEquals("X", result.getContent().getName());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById(10L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.getById(10L));
    }

    @Test
    void testDeleteById() {
        service.deleteById(5L);
        verify(repository).deleteById(5L);
    }

    @Test
    void testDeleteAll() {
        service.deleteAll();
        verify(repository).deleteAll();
    }

}
