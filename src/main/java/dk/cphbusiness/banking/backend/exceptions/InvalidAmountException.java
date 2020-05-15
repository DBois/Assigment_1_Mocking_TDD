package dk.cphbusiness.banking.backend.exceptions;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;

public class InvalidAmountException extends Exception {
    private int statusCode;

    public InvalidAmountException(String msg, int statusCode){
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
