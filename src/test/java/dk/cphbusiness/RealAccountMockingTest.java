package dk.cphbusiness;

import dk.cphbusiness.banking.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RealAccountMockingTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void testAccountTransferWithAccountNumber(){
        //Arrange
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new RealClock();
        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212", clock);
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER, clock);
        context.checking(new Expectations(){{
            oneOf(BANK).getAccount(TARGET_NUMBER);
            will(returnValue(target));
            // oneOf(BANK).getName();
        }});

        //Act
        source.transfer(10000L, TARGET_NUMBER);

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());

    }

    @Test
    public void testAccountTransferWithAccount(){
        //Arrange
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new RealClock();
        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212", clock);
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER, clock);

        //Act
        source.transfer(10000L, target);

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }
}
