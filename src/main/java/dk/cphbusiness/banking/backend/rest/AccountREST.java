package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.facade.AccountFacade;
import dk.cphbusiness.banking.backend.utility.TransferDTO;
import dk.cphbusiness.banking.contract.MovementManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/accounts")
public class AccountREST {
    AccountFacade af = new AccountFacade();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{number}")
    public Response getAccount(@PathParam("number") String number) {
        try {
            var acc = af.getAccount(number);
            return Response.ok().entity(GSON.toJson(acc)).build();
        } catch (RestException ex) {
            return ex.toResponse(GSON);
        } catch (Exception e) {
            return Response.status(404).entity(GSON.toJson(e)).build();
        }
    }

    @GET
    @Path("/customer={cpr}")
    public Response getAccounts(@PathParam("cpr") String cpr) {
        try {
            System.out.println(cpr);
            var accounts = af.getAccountsFromCustomer(cpr);
            return Response.ok().entity(GSON.toJson(accounts)).build();
        } catch (RestException ex) {
            return ex.toResponse(GSON);
        } catch (Exception e) {
            return Response.status(404).entity(GSON.toJson(e)).build();
        }
    }

    @POST
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(String transferString) {
        try {
            var transferDTO = GSON.fromJson(transferString, TransferDTO.class);
            var movement = af.transfer(transferDTO.getAmount(), transferDTO.getSource(), transferDTO.getTarget());
            return Response.ok().entity(GSON.toJson(movement)).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(404).entity(GSON.toJson(e)).build();
        }
    }





}
