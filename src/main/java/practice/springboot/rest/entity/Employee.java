package practice.springboot.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {

	private @Id @GeneratedValue Long id;
	private String name;
	private String role;

	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}

}
