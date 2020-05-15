package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.doubles.*;
import dk.cphbusiness.banking.backend.exceptions.InvalidAmountException;
import dk.cphbusiness.banking.backend.models.*;
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
        //Assemble
        Bank bank = null;
        Customer customer =  new CustomerDummy();
        String number = null;

        //Act
        RealAccount account = new RealAccount(bank, customer, number);

        //Assert
        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithBank(){
        //Assemble
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;

        //Act
        RealAccount account = new RealAccount(bank, customer, number);

        //Assert
        assertEquals(bank, account.getBank());
        assertNotNull(account.getBank());
    }

    @Test
    public void testCreateAccountWithCustomer(){
        //Assemble
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;

        //Act
        RealAccount account = new RealAccount(bank, customer, number);

        //Assert
        assertEquals(customer, account.getCustomer());
        assertNotNull(account.getCustomer());
    }

    @Test
    public void testCreateAccountWithNumber(){
        //Assemble
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";

        //Act
        RealAccount account = new RealAccount(bank, customer, number);

        //Assert
        assertEquals(number, account.getNumber());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testCreateAccountWithZeroBalance(){
        //Assemble
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";

        //Act
        RealAccount account = new RealAccount(bank, customer, number);

        //Assert
        assertEquals(0L, account.getBalance());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testTransferPositiveAmount() throws InvalidAmountException {
        //Assemble
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        var clock = new ClockStub();
        RealAccount source = new RealAccount(bank, customer, "SRC12345");
        RealAccount target = new RealAccount(bank, customer, "TGT12345");

        //Act
        source.transfer(10000L, target, clock.getTime());

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testTransferPositiveAmountUsingNumber() throws InvalidAmountException {
        //Assemble
        Bank bankDummy = new BankDummy();
        BankStub bank = new BankStub();
        Customer customer = new CustomerDummy();
        String targetNumber = "SRC12345";
        var clock = new ClockStub();
        RealAccount target = new RealAccount(bankDummy, customer, targetNumber);
        RealAccount source = new RealAccount(bank, customer, "EWQEW21321");
        bank.setAccount(target);

        //Act
        source.transfer(10000L, targetNumber, clock.getTime());

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testAccountTransferWithAccountNumber() throws InvalidAmountException {
        //Assemble
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new ClockStub();
        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212");
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER);

        context.checking(new Expectations(){{
            oneOf(BANK).getAccount(TARGET_NUMBER);
            will(returnValue(target));
            // oneOf(BANK).getName();
        }});

        //Act
        source.transfer(10000L, TARGET_NUMBER, clock.getTime());

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testAccountTransferWithAccount() throws InvalidAmountException {
        //Assemble
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        final String TARGET_NUMBER = "TGT54321";
        var clock = new ClockStub();
        RealAccount source = new RealAccount(BANK, CUSTOMER, "DPG43212");
        RealAccount target = new RealAccount(BANK, CUSTOMER, TARGET_NUMBER);

        //Act
        source.transfer(10000L, target, clock.getTime());

        //Assert
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }

    @Test
    public void testGetBalance(){
        //Assemble
        final Customer CUSTOMER = context.mock(Customer.class);
        final Bank BANK = context.mock(Bank.class);
        var account = new RealAccount(BANK, CUSTOMER, "12345697");

        //Act
        account.updateBalance(200);

        //Assert
        assertEquals(200, account.getBalance());
    }

    @Test
    public void testGetMovements() throws InvalidAmountException {
        //Assemble
        var bank = new BankDummy();
        var customerSource = new CustomerStub("100895-6666", "Adam");
        var customerTarget = new CustomerStub("100885-6666", "Adam2");
        var source = new RealAccount(bank, customerSource, "12345");
        var target = new RealAccount(bank, customerTarget, "12045");
        var target2 = new RealAccount(bank, customerTarget, "12046");
        var clock = new ClockStub();
        source.transfer(500L, target, clock.getTime());
        source.transfer(5000L, target, clock.getTime());
        source.transfer(5000L, target2, clock.getTime());

        //Act
        var movements = source.getMovements();

        //Assert
        var expectedAccountNumber = movements.get(2).getTarget();
        var expectedTime = movements.get(2).getTime();
        assertEquals(3, source.getMovements().size());
        assertEquals("12046",expectedAccountNumber );
        assertEquals(1585812373273L, expectedTime );
    }
}
