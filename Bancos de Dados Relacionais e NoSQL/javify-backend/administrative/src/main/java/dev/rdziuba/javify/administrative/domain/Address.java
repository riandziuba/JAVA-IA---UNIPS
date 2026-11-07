package dev.rdziuba.javify.administrative.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adress")
public class Address {

    @Id
    @Column(name = "postal_code", length = 8)
    private String postalCode;

    @Column(length = 150)
    private String street;

    @Column(length = 100)
    private String neighborhood;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public String toString() {
        return "Address{postalCode='%s', street='%s', neighborhood='%s'}"
                .formatted(postalCode, street, neighborhood);
    }
}
