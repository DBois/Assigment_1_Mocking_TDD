package dk.cphbusiness.banking.backend;
import dk.cphbusiness.banking.backend.doubles.AccountDummy;
import dk.cphbusiness.banking.backend.models.RealMovement;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealMovementTest {

    @Test
    public void testCreateMovement(){
        var timeStamp = 0L;
        var amount = 0L;
        AccountDummy target = null;
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, target, source);
        assertNotNull(movement);

    }
    @Test
    public void testGetTimestamp(){
        var timeStamp = 10000000L;
        var amount = 0L;
        AccountDummy target = null;
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, target, source);
        assertEquals(10000000L, movement.getTime());
    }
    @Test
    public void testGetAmount(){
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = null;
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, target, source);
        assertEquals(10000L, movement.getAmount());
    }

    @Test
    public void testGetTarget(){
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = new AccountDummy();
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, source, target);
        assertEquals(target, movement.getTarget());
    }

    @Test
    public void testGetSource(){
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = new AccountDummy();
        AccountDummy source = new AccountDummy();
        var movement = new RealMovement(0, timeStamp, amount, source, target);
        assertEquals(source, movement.getSource());
    }



}
