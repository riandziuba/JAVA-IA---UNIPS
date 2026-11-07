package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
    Address findByPostalCode(String postalCode);
}
