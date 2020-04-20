package dk.cphbusiness.banking.backend.app;

// import the rest service you created!
import dk.cphbusiness.banking.backend.REST.HelloRestService;
import dk.cphbusiness.banking.backend.REST.AccountREST;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our hello service
        singletons.add(new HelloRestService());
        singletons.add(new AccountREST());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
