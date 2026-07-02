package dev.rdziuba;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://swapi.info/api")
public interface StarWarsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("starships")
    public String getStarships();
}
