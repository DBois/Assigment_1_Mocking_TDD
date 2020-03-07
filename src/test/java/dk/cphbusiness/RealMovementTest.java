package dk.cphbusiness;
import dk.cphbusiness.banking.Movement;
import dk.cphbusiness.banking.RealMovement;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealMovementTest {

    @Test
    public void testCreateMovement(){
        var timeStamp = 0L;
        var amount = 0L;
        AccountDummy target = null;
        var movement = new RealMovement(timeStamp, amount, target);
        assertNotNull(movement);
    }
    @Test
    public void testGetTimestamp(){
        var timeStamp = 10000000L;
        var amount = 0L;
        AccountDummy target = null;
        var movement = new RealMovement(timeStamp, amount, target);
        assertEquals(10000000L, movement.getTime());
    }
    @Test
    public void testGetAmount(){
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = null;
        var movement = new RealMovement(timeStamp, amount, target);
        assertEquals(10000L, movement.getAmount());
    }
    @Test
    public void testGetTarget(){
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = new AccountDummy();
        var movement = new RealMovement(timeStamp, amount, target);
        assertEquals(target, movement.getTarget());
    }



}
