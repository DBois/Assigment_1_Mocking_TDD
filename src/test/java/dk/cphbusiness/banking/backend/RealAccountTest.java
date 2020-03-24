package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.doubles.*;
import dk.cphbusiness.banking.backend.models.Clock;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealAccountTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void testCreateAccount() throws Exception {
        Bank bank = null;
        Customer customer =  new CustomerDummy();
        String number = null;
        var clock = new ClockDummy();

        RealAccount account = new RealAccount(bank, customer, number, clock);

        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithBank(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        var clock = new ClockDummy();

        RealAccount account = new RealAccount(bank, customer, number, clock);

        assertEquals(bank, account.getBank());
        assertNotNull(account.getBank());
    }

    @Test
    public void testCreateAccountWithCustomer(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        var clock = new ClockDummy();

        RealAccount account = new RealAccount(bank, customer, number, clock);

        assertEquals(customer, account.getCustomer());
        assertNotNull(account.getCustomer());
    }

    @Test
    public void testCreateAccountWithNumber(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        var clock = new ClockDummy();

        RealAccount account = new RealAccount(bank, customer, number, clock);

        assertEquals(number, account.getNumber());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testCreateAccountWithZeroBalance(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        var clock = new ClockDummy();

        RealAccount account = new RealAccount(bank, customer, number, clock);

        assertEquals(0L, account.getBalance());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testTransferPositiveAmount(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        var clock = new ClockDummy();

        RealAccount source = new RealAccount(bank, customer, "SRC12345", clock);
        RealAccount target = new RealAccount(bank, customer, "TGT12345", clock);

        source.transfer(10000L, target);
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testTransferPositiveAmountUsingNumber(){
        Bank bankDummy = new BankDummy();
        BankStub bank = new BankStub();

        Customer customer = new CustomerDummy();
        String targetNumber = "SRC12345";
        var clock = new ClockDummy();

        RealAccount target = new RealAccount(bankDummy, customer, targetNumber, clock);
        RealAccount source = new RealAccount(bank, customer, "EWQEW21321", clock);
        bank.setAccount(target);

        source.transfer(10000L, targetNumber);
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testAccountTransferWithAccountNumber(){
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new ClockDummy();
        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212", clock);
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER, clock);

        context.checking(new Expectations(){{
            oneOf(BANK).getAccount(TARGET_NUMBER);
            will(returnValue(target));
            // oneOf(BANK).getName();
        }});

        source.transfer(10000L, TARGET_NUMBER);
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testAccountTransferWithAccount(){
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new ClockDummy();

        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212", clock);
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER, clock);
        source.transfer(10000L, target);

        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testGetBalance(){
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);

        var account = new RealAccount(BANK, CUSTOMER, "12345697", new ClockDummy());
        account.updateBalance(200);

        assertEquals(200, account.getBalance());
    }

    @Test
    public void testGetMovements(){
        var bank = new BankDummy();
        final var CLOCK = context.mock(Clock.class);

        var customerSource = new CustomerStub("100895-6666", "Adam");
        var customerTarget = new CustomerStub("100885-6666", "Adam2");
        var source = new RealAccount(bank, customerSource, "12345", CLOCK);
        var target = new RealAccount(bank, customerTarget, "12045", CLOCK);
        var target2 = new RealAccount(bank, customerTarget, "12046", CLOCK);

        context.checking(new Expectations(){{
            allowing(CLOCK).getTime();
            will(returnValue(1234567890L));
            // oneOf(BANK).getName();
        }});

        source.transfer(500L, target);
        source.transfer(5000L, target);
        source.transfer(5000L, target2);

        var expectedAccountNumber = source.getMovements().get(2).getTarget().getNumber();
        var expectedTime = source.getMovements().get(2).getTime();

        assertEquals(3, source.getMovements().size());
        assertEquals("12046",expectedAccountNumber );
        assertEquals(1234567890L ,expectedTime );
    }
}
