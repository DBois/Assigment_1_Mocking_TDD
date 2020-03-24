package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.contract.CustomerManagerDummy;
import dk.cphbusiness.banking.contract.CustomerManagerHolder;
import dk.cphbusiness.banking.contract.CustomerManagerTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerManagerTest.class
})

public class CustomerManagerDummyTest {
    @BeforeClass
    public static void setupClass() {
        CustomerManagerHolder.customerManager = new CustomerManagerDummy();
    }
}
