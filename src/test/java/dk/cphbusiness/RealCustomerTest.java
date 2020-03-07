package dk.cphbusiness;
import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.RealCustomer;
import org.jmock.Expectations;
import org.jmock.api.Invocation;
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
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank);
        assertNotNull(customer);
    }

    @Test
    public void testGetName(){
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank);
        assertEquals(customer.getName(), "Adam");
    }
    
    @Test
    public void testGetCpr(){
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank);
        assertEquals(customer.getCpr(), "100895-6666");
    }

    @Test
    public void testGetAllAccountNumbers(){
        var bank = new BankDummy();
        var customer = new RealCustomer("100895-6666", "Adam", bank){{
            addAccountNumber("123abc");
            addAccountNumber("321abc");
        }};

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

        context.checking(new Expectations(){{
            oneOf(BANK).getAccount(targetAccount);
            will(returnValue(ACCOUNT_TARGET));

            oneOf(ACCOUNT_SOURCE).transfer(amount, ACCOUNT_TARGET);

            oneOf(ACCOUNT_SOURCE).getBalance();
            will(returnValue(-amount));

            oneOf(ACCOUNT_TARGET).getBalance();
            will(returnValue(amount));
        }});

        source.transfer(10000L, ACCOUNT_SOURCE, target);
        assertEquals(-10000L, ACCOUNT_SOURCE.getBalance());
        assertEquals(10000L, ACCOUNT_TARGET.getBalance());
    }

}
