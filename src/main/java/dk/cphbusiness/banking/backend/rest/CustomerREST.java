package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.facade.AccountFacade;
import dk.cphbusiness.banking.backend.facade.CustomerFacade;
import dk.cphbusiness.banking.backend.models.RealCustomer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static dk.cphbusiness.banking.contract.CustomerManager.*;

@Path("/customers")
public class CustomerREST {
    CustomerFacade cf = new CustomerFacade();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{cpr}")
    public Response getCustomer(@PathParam("cpr") String cpr) {
        try {
            var customer = cf.getCustomer(cpr);
            return Response.ok().entity(GSON.toJson(customer)).build();
        } catch (RestException ex) {
            return ex.toResponse(GSON);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(404).entity(GSON.toJson(e)).build();
        }
    }


    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(RealCustomer customer) {
        System.out.println(customer.toString());
        try {
            var customerDetail = cf.updateCustomer(customer);
            return Response.ok().entity(GSON.toJson(customerDetail)).build();
        } catch (Exception e) {
            return Response.status(403).entity(GSON.toJson(e)).build();
        }
    }

}
