package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testCreateAccount() throws Exception {
        Bank bank = null;
        Customer customer =  new CustomerDummy();
        String number = null;
        Account account = new Account(bank, customer, number);
        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithBank(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        Account account = new Account(bank, customer, number);
        assertEquals(bank, account.getBank());
        assertNotNull(account.getBank());
    }

    @Test
    public void testCreateAccountWithCustomer(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        Account account = new Account(bank, customer, number);
        assertEquals(customer, account.getCustomer());
        assertNotNull(account.getCustomer());
    }

    @Test
    public void testCreateAccountWithNumber(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        Account account = new Account(bank, customer, number);
        assertEquals(number, account.getNumber());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testCreateAccountWithZeroBalance(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        Account account = new Account(bank, customer, number);
        assertEquals(0L, account.getBalance());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testTransferPositiveAmount(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        Account source = new Account(bank, customer, "SRC12345");
        Account target = new Account(bank, customer, "TGT12345");
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

        Account target = new Account(bankDummy, customer, targetNumber);
        Account source = new Account(bank, customer, "EWQEW21321");
        bank.setAccount(target);
        source.transfer(10000L, targetNumber);
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }
}
