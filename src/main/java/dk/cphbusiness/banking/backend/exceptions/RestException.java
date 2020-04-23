package dk.cphbusiness.banking.backend.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RestException extends Exception {
    private int statusCode;

    public RestException(String msg, int statusCode){
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return this.statusCode;
    }

    public Response toResponse(Gson GSON){
        return Response.status(this.statusCode).entity(GSON.toJson(this)).build();

    }
}
