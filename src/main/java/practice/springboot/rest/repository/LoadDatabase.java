package practice.springboot.rest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import practice.springboot.rest.entity.Employee;

@Configuration
public class LoadDatabase {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {
    return args -> {
        LOGGER.info("Preloading {}", repository.save(new Employee("Bilbo Bagginess", "burglar")));
        LOGGER.info("Preloading {}", repository.save(new Employee("Frodo Bagginess", "thief")));
    };
  }

}
