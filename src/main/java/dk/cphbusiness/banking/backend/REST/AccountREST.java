package dk.cphbusiness.banking.backend.REST;

import dk.cphbusiness.banking.backend.facade.AccountFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/accounts")
public class AccountREST{
    AccountFacade af;

    public AccountREST() {
        this.af = new AccountFacade();
    }

    @GET
    @Path("/get/{id}")
    public Response getAccount(@PathParam("id") String accountName) {
        try {
            var acc = af.getAccount(accountName);
            return Response.ok()
                    .entity(acc)
                    .build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity(e)
                    .build();
        }
    }

    @GET
    @Path("/get")
    public Response getAccounts(String s) {
        return null;
    }


    public Response transfer(long l, String s, String s1) {
        return null;
    }
}
