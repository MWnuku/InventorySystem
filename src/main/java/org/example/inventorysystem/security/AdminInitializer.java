package org.example.inventorysystem.security;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.models.Role;
import org.example.inventorysystem.respositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AdminInitializer {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AdminInitializer(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public CommandLineRunner initAdminUser(PersonRepository personRepository) {
		return args -> {

			Optional<Person> adminUserOptional = personRepository.findByEmail("admin123@example" +
					".com");
			if (adminUserOptional.isEmpty()) {
				Person adminUser = new Person();
				adminUser.setFirstName("Admin123");
				adminUser.setLastName("User123");
				adminUser.setEmail("admin123@example.com");
				adminUser.setUnit("unit");
				adminUser.setPassword(passwordEncoder.encode("admin1234")); // Set default password
				adminUser.setRole(Role.Admin); // Assign admin role
				personRepository.save(adminUser);
				System.out.println("Admin user created with email: admin@example.com and password: admin123");
			}
		};
	}
}
