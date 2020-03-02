package dk.cphbusiness;

import dk.cphbusiness.banking.RealBank;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealBankTest {

    @Test
    public void testCreateBank() throws Exception {
        RealBank rb = new RealBank("Nordea");
        assertNotNull(rb);
    }

    @Test
    public void testRegisterAndGetAccount() throws Exception{
        //Arrange
        RealBank rb = new RealBank("Nordea");
        BankDummy bd = new BankDummy();
        CustomerDummy cd = new CustomerDummy();
        String number1 = "AD12345";
        String number2 = "AD54321";

        AccountStub ad1 = new AccountStub(bd, cd, number1);
        AccountStub ad2 = new AccountStub(bd, cd, number2);

        //Act
        rb.registerAccount(ad1);
        rb.registerAccount(ad2);

        //Assert
        assertEquals(rb.getAccount(number1), ad1);
        assertEquals(rb.getAccount(number2), ad2);
    }

    @Test
    public void testGetAccounts() throws Exception{
        //Arrange
        RealBank rb = new RealBank("Nordea");
        BankDummy bd = new BankDummy();
        String cpr = "123456789";
        String name = "Emilo";
        CustomerStub cs = new CustomerStub(cpr, name);

        String number1 = "AD12345";
        String number2 = "AD54321";
        AccountStub ad1 = new AccountStub(bd, cs, number1);
        AccountStub ad2 = new AccountStub(bd, cs, number2);
        cs.addAccountName(number1);
        cs.addAccountName(number2);

        //Act
        rb.registerAccount(ad1);
        rb.registerAccount(ad2);

        //Assert
        assertTrue(rb.getAccounts(cs).contains(ad1));
        assertTrue(rb.getAccounts(cs).contains(ad2));
    }

    @Test
    public void testRegisterAndGetCustomer() throws Exception{
        //Arrange
        RealBank rb = new RealBank("Nordea");
        String cpr = "123456789";
        String name = "Emilo";
        CustomerStub cs = new CustomerStub(cpr, name);

        //Act
        rb.registerCustomer(cs);

        //Assert
        assertEquals(rb.getCustomer(cpr), cs);
    }

    @Test
    public void testGetName() throws Exception {
        //Arrange
        RealBank rb = new RealBank("Nordea");


        //Act
        var name = rb.getName();

        //Assert
        assertEquals("Nordea", name);
    }
}
