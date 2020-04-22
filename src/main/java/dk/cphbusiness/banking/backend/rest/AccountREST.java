package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.banking.backend.facade.AccountFacade;
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
        } catch (Exception e) {
            return Response.status(404).entity(GSON.toJson(e)).build();
        }
    }

    @GET
    @Path("")
    public Response getAccounts(String s) {
        return null;
    }


    public Response transfer(long l, String s, String s1) {
        return null;
    }


}
