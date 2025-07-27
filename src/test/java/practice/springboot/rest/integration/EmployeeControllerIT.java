package practice.springboot.rest.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import practice.springboot.rest.entity.Employee;
import practice.springboot.rest.repository.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIT {

    @Autowired private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testAddAndGetEmployee() throws Exception {
        String empJson = """
                {
                    "name": "Alice",
                    "role": "Manager"
                }
                """;

        mockMvc.perform(post("/api/v1/employee/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(empJson))
                .andExpect(status().isOk());

        List<Employee> saved = repository.findAll();
        assertEquals(1, saved.size());
    }

    @Test
    void testGetAllEmployees() throws Exception {
        repository.saveAll(List.of(new Employee("Bob", "Dev"), new Employee("Eve", "QA")));

        mockMvc.perform(get("/api/v1/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.employeeList").isArray())
                .andExpect(jsonPath("$._embedded.employeeList.length()").value(2));
    }

    @Test
    void testGetById() throws Exception {
        Employee saved = repository.save(new Employee("Tom", "Engineer"));

        mockMvc.perform(get("/api/v1/employee/id/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.role").value("Engineer"));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        mockMvc.perform(get("/api/v1/employee/id/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find employee with ID: 999"));
    }

    @Test
    void testDeleteById() throws Exception {
        Employee emp = repository.save(new Employee("ToDelete", "Intern"));

        mockMvc.perform(delete("/api/v1/employee/id/{id}", emp.getId()))
                .andExpect(status().isOk());

        assertFalse(repository.findById(emp.getId()).isPresent());
    }

    @Test
    void testDeleteAll() throws Exception {
        repository.save(new Employee("Emp1", "A"));
        repository.save(new Employee("Emp2", "B"));

        mockMvc.perform(delete("/api/v1/employee/all"))
                .andExpect(status().isOk());

        assertTrue(repository.findAll().isEmpty());
    }

}
