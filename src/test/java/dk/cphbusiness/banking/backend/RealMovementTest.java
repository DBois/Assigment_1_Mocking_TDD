package dk.cphbusiness.banking.backend;
import dk.cphbusiness.banking.backend.doubles.AccountDummy;
import dk.cphbusiness.banking.backend.models.RealMovement;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealMovementTest {

    @Test
    public void testCreateMovement(){
        //Assemble
        var timeStamp = 0L;
        var amount = 0L;
        AccountDummy target = null;
        AccountDummy source = null;

        //Act
        var actual = new RealMovement(0, timeStamp, amount, target, source);

        //Assert
        assertNotNull(actual);

    }
    @Test
    public void testGetTimestamp(){
        //Assemble
        var timeStamp = 10000000L;
        var amount = 0L;
        AccountDummy target = null;
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, target, source);

        //Act
        var actual = movement.getTime();

        //Assert
        assertEquals(10000000L, actual);
    }
    @Test
    public void testGetAmount(){
        //Assemble
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = null;
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, target, source);

        //Act
        var actual = movement.getAmount();

        //Assert
        assertEquals(10000L, actual);
    }

    @Test
    public void testGetTarget(){
        //Assemble
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = new AccountDummy();
        AccountDummy source = null;
        var movement = new RealMovement(0, timeStamp, amount, source, target);

        //Act
        var actual = movement.getTarget();

        //Assert
        assertEquals(target, actual);
    }

    @Test
    public void testGetSource(){
        //Assemble
        var timeStamp = 10000000L;
        var amount = 10000L;
        AccountDummy target = new AccountDummy();
        AccountDummy source = new AccountDummy();
        var movement = new RealMovement(0, timeStamp, amount, source, target);

        //Act
        var actual = movement.getSource();

        //Assert
        assertEquals(source, actual);
    }



}
