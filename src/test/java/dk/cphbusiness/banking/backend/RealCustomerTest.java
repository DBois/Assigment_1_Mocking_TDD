package dk.cphbusiness.banking.backend;
import dk.cphbusiness.banking.backend.doubles.BankDummy;
import dk.cphbusiness.banking.backend.doubles.ClockStub;
import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.RealCustomer;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RealCustomerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void testCreateCustomer(){
        //Assemble
        var bank = new BankDummy();

        //Act
        var customer = new RealCustomer("100895-6666", "Adam", bank);

        //Assert
        assertNotNull(customer);
    }

    @Test
    public void testGetName(){
        //Assemble
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank);

        //Act
        var name = customer.getName();

        //Assert
        assertEquals(name, "Adam");
    }
    
    @Test
    public void testGetCpr(){
        //Assemble
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank);

        //Act
        var cpr = customer.getCpr();

        //Assert
        assertEquals(cpr, "100895-6666");
    }

    @Test
    public void testGetAllAccountNumbers(){
        //Assemble
        var bank = new BankDummy();

        //Act
        var customer = new RealCustomer("100895-6666", "Adam", bank){{
            addAccountNumber("123abc");
            addAccountNumber("321abc");
        }};

        //Assert
        var expected = new ArrayList<String>() {{
            add("123abc");
            add("321abc");
        }};
        var actual = customer.getAccountNumbers();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testTransfer(){
        //Assemble
        final Account ACCOUNT_SOURCE = context.mock(Account.class, "account source");
        final Account ACCOUNT_TARGET = context.mock(Account.class, "account target");
        final Bank BANK = context.mock(Bank.class);
        var source = new RealCustomer("100895-6666", "Adam", BANK){{
            addAccountNumber("ABC2345");
        }};
        var target = new RealCustomer("200286-6666", "Sebbelicious", BANK){{
            addAccountNumber("ABC1234");
        }};
        var amount = 10000L;
        var targetAccount = target.getAccountNumbers().get(0);
        var clock = new ClockStub();

        context.checking(new Expectations(){{
            oneOf(BANK).getAccount(targetAccount);
            will(returnValue(ACCOUNT_TARGET));

            oneOf(ACCOUNT_SOURCE).transfer(amount, ACCOUNT_TARGET, clock.getTime());

            oneOf(ACCOUNT_SOURCE).getBalance();
            will(returnValue(-amount));

            oneOf(ACCOUNT_TARGET).getBalance();
            will(returnValue(amount));
        }});

        //Act
        source.transfer(10000L, ACCOUNT_SOURCE, target, clock.getTime());

        //Assert
        assertEquals(-10000L, ACCOUNT_SOURCE.getBalance());
        assertEquals(10000L, ACCOUNT_TARGET.getBalance());
    }

}
