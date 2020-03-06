package dk.cphbusiness;

import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealAccountTest {

    @Test
    public void testCreateAccount() {
        Bank bank = null;
        Customer customer =  new CustomerDummy();
        String number = null;
        RealAccount realAccount = new RealAccount(bank, customer, number);
        assertNotNull(realAccount);
    }

    @Test
    public void testCreateAccountWithBank(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        RealAccount realAccount = new RealAccount(bank, customer, number);
        assertEquals(bank, realAccount.getBank());
        assertNotNull(realAccount.getBank());
    }

    @Test
    public void testCreateAccountWithCustomer(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = null;
        RealAccount realAccount = new RealAccount(bank, customer, number);
        assertEquals(customer, realAccount.getCustomer());
        assertNotNull(realAccount.getCustomer());
    }

    @Test
    public void testCreateAccountWithNumber(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        RealAccount realAccount = new RealAccount(bank, customer, number);
        assertEquals(number, realAccount.getNumber());
        assertNotNull(realAccount.getNumber());
    }

    @Test
    public void testCreateAccountWithZeroBalance(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        String number = "ABC12345";
        RealAccount realAccount = new RealAccount(bank, customer, number);
        assertEquals(0L, realAccount.getBalance());
        assertNotNull(realAccount.getNumber());
    }

    @Test
    public void testTransferPositiveAmount(){
        Bank bank = new BankDummy();
        Customer customer = new CustomerDummy();
        RealAccount source = new RealAccount(bank, customer, "SRC12345");
        RealAccount target = new RealAccount(bank, customer, "TGT12345");
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

        RealAccount target = new RealAccount(bankDummy, customer, targetNumber);
        RealAccount source = new RealAccount(bank, customer, "EWQEW21321");
        bank.setAccount(target);
        source.transfer(10000L, targetNumber);
        assertEquals(-10000L, source.getBalance());
        assertEquals(10000L, target.getBalance());
    }
}
