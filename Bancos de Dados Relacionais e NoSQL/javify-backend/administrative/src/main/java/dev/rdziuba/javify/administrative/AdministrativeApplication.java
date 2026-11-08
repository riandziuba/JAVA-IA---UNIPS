package dev.rdziuba.javify.administrative;

import dev.rdziuba.javify.administrative.demo.JdbcAccess;
import dev.rdziuba.javify.administrative.demo.JpaDemo;
import dev.rdziuba.javify.administrative.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdministrativeApplication {

	public AdministrativeApplication() {
	}

	static void main(String[] args) {
		SpringApplication.run(AdministrativeApplication.class, args);
	}
}
