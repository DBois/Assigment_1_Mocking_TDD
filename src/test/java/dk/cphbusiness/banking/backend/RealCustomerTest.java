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
        var customer = new RealCustomer("100895-6666", "Adam");

        //Assert
        assertNotNull(customer);
    }

    @Test
    public void testGetName(){
        //Assemble
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam");

        //Act
        var name = customer.getName();

        //Assert
        assertEquals(name, "Adam");
    }
    
    @Test
    public void testGetCpr(){
        //Assemble
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam");

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
        var customer = new RealCustomer("100895-6666", "Adam"){{
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


}
