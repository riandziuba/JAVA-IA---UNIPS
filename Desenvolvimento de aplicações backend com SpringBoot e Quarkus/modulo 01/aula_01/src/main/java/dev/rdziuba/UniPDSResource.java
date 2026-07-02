package dev.rdziuba;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/unipds")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class UniPDSResource {

    private int i = 0;

    @GET
    public int getI() {
        return this.i;
    }

    @GET
    @Path("/getIDifferent")
    public int getIDifferent() {
        return LocalDateTime.now().getNano();
    }

    @POST
    public void addI() {
        this.i++;
    }

    @DELETE
    public void removeI() {
        this.i--;
    }

    @PUT
    public void setI(int i) {
        this.i = i;
    }
}
