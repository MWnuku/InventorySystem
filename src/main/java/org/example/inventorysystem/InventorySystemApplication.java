package org.example.inventorysystem;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.models.Role;
import org.example.inventorysystem.respositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InventorySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySystemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Person admin = new Person();
			admin.setEmail("admin@example.com");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole(Role.Admin);

			Person user = new Person();
			user.setEmail("user@example.com");
			user.setPassword(passwordEncoder.encode("user123"));
			user.setRole(Role.User);

			personRepository.save(admin);
			personRepository.save(user);
		};
	}


}
