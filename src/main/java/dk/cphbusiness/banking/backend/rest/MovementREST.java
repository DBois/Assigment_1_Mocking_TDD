package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.facade.MovementFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.util.List;

@Path("/movements")
public class MovementREST {
    MovementFacade mf = new MovementFacade();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{accountNumber}")
    public Response getMovements(@PathParam("accountNumber") String accountNumber) {

        try {
            var movement = mf.getMovements(accountNumber);
            return Response.ok().entity(GSON.toJson(movement)).build();
        } catch (RestException ex) {
            return ex.toResponse(GSON);
        } catch (Exception ex) {
            return Response.status(404).entity(GSON.toJson(ex)).build();
        }
    }

}
