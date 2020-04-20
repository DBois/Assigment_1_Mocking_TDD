package dk.cphbusiness.banking.backend.REST;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RESTApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public RESTApplication() {
        // Register our hello service
        singletons.add(new HelloRestService());
        singletons.add(new AccountREST());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
