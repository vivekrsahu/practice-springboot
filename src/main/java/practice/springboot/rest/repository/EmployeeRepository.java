package practice.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practice.springboot.rest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
