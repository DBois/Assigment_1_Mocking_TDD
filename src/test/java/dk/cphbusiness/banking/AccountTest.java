package dk.cphbusiness.banking;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testCreateAccount() throws Exception {
        Bank bank = null;
        Customer customer = null;
        String number = null;
        Account account = new Account(bank, customer, number);
        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithBank(){
        Bank bank = new BankDummy();
        Customer customer = null;
        String number = null;
        Account account = new Account(bank, customer, number);
        assertEquals(bank, account.getBank());
        assertNotNull(account.getBank());
    }
}
