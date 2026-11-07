package dev.rdziuba.javify.administrative;

import dev.rdziuba.javify.administrative.demo.JdbcAccess;
import dev.rdziuba.javify.administrative.demo.JpaDemo;
import dev.rdziuba.javify.administrative.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdministrativeApplication implements CommandLineRunner {

	private final JdbcAccess jdbcAccess;
	private final AddressRepository addressRepository;
	private final JpaDemo jpaDemo;

	public AdministrativeApplication(JdbcAccess jdbcAccess, AddressRepository addressRepository, JpaDemo jpaDemo) {
		this.jdbcAccess = jdbcAccess;
		this.addressRepository = addressRepository;
		this.jpaDemo = jpaDemo;
	}

    public static void main(String[] args) {
		SpringApplication.run(AdministrativeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jpaDemo.findSignatureWithPlan();
	}
}
