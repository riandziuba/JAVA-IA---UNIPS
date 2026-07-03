package dev.rdziuba.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public int birthYear;

    public static List<Person> findByBirthYear(int birthYear) {
        return find("birthYear", birthYear).list();
    }
}
