package dk.cphbusiness.banking.backend.rest;

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
        singletons.add(new HelloRestService());
        singletons.add(new AccountREST());
        singletons.add(new CustomerREST());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
