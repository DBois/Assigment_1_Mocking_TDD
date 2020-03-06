package dk.cphbusiness;

import dk.cphbusiness.banking.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class RealCustomerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void testCreateCustomer() {
        String cpr = "1111111111";
        String name = "Kurt";
        BankDummy bd = new BankDummy();
        RealCustomer c = new RealCustomer(cpr, name, bd);
        assertNotNull(c);
    }

    @Test
    public void testGetCpr() {
        String cpr = "1111111111";
        String name = "Kurt";
        BankDummy bd = new BankDummy();
        RealCustomer c = new RealCustomer(cpr, name, bd);
        String res = c.getCpr();
        assertEquals(res, cpr);
    }

    @Test
    public void testGetName() {
        String cpr = "1111111111";
        String name = "Kurt";
        BankDummy bd = new BankDummy();
        RealCustomer c = new RealCustomer(cpr, name, bd);
        String res = c.getName();
        assertEquals(res, name);
    }

    @Test
    public void testAccountNumbers() {
        //Arrange
        String cpr = "1111111111";
        String name = "Kurt";
        BankDummy bd = new BankDummy();

        var expected = new ArrayList<String>() {{
            add("AC12345");
            add("AC123456");
            add("AC1234567");
            add("AC12345678");
        }};


        //Act
        RealCustomer c = new RealCustomer(cpr, name, bd) {{
            for (var accountNumber : expected) {
                addAccountNumber(accountNumber);
            }
        }};

        //Assert
        assertEquals(c.getAccountNumbers(), expected);
    }

    @Test
    public void testTransfer(){
        //Arrange
        final Bank BANK = context.mock(Bank.class);
        RealCustomer customerSource = new RealCustomer("123456", "customerSource", BANK);
        RealCustomer customerTarget = new RealCustomer("654321", "customerTarget", BANK);
        customerTarget.addAccountNumber("TARGET");

        final Account SOURCE = context.mock(Account.class, "SOURCE");
        final Account TARGET = context.mock(Account.class, "TARGET");

        var amount = 1000L;
        context.checking(new Expectations(){{
            oneOf(SOURCE).updateBalance(-amount);
            oneOf(TARGET).updateBalance(amount);

            oneOf(BANK).getAccount(customerTarget.getAccountNumbers().get(0));
            will(returnValue(TARGET));

            oneOf(SOURCE).getBalance();
            will(returnValue(-amount));

            oneOf(TARGET).getBalance();
            will(returnValue(amount));

        }});




        //Act
        customerSource.transfer(amount, SOURCE, customerTarget);

        //Assert
        assertEquals(-1000L, SOURCE.getBalance());
        assertEquals(1000L, TARGET.getBalance());
    }




}
