package dev.rdziuba;

import dev.rdziuba.entities.Person;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> getPerson() {
        return Person.listAll();
    }

    @GET
    @Path("findByBirthYear")
    public List<Person> findByBirthYear(@QueryParam("birthYear") int birthYear) {
        return Person.findByBirthYear(birthYear);
    }

    @POST
    @Transactional
    public Person addPerson(Person person) {
        person.id = null;
        person.persist();

        return person;
    }

    @PUT
    @Transactional
    public Person updatePerson(Person person) {
        Person personDB = Person.findById(person.id);
        personDB.name = person.name;
        personDB.birthYear = person.birthYear;
        personDB.persist();

        return personDB;
    }

    @DELETE
    @Transactional
    public void deletePerson(int id) {
        Person personDB = Person.findById(id);
        personDB.delete();
    }
}
