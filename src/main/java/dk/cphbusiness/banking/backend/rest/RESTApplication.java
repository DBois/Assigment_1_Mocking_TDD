package dk.cphbusiness.banking.backend.rest;

import dk.cphbusiness.banking.backend.rest.cors.CorsRequestFilter;
import dk.cphbusiness.banking.backend.rest.cors.CorsResponseFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RESTApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public RESTApplication() throws ClassNotFoundException {
        // Load database driver so it initialises
        Class.forName("org.postgresql.Driver");
        singletons.add(new CorsRequestFilter());
        singletons.add(new CorsResponseFilter());
        singletons.add(new AccountREST());
        singletons.add(new MovementREST());
        singletons.add(new CustomerREST());

    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
