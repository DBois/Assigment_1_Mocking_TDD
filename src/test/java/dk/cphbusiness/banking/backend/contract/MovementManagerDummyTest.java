package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.contract.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MovementManagerTest.class
})

public class MovementManagerDummyTest {
    @BeforeClass
    public static void setupClass(){
        MovementManagerHolder.movementManager = new MovementManagerDummy();
    }
}
