package dk.cphbusiness;

import dk.cphbusiness.banking.RealBank;
import dk.cphbusiness.banking.RealCustomer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RealCustomerTest {

    @Test
    public void testCreateCustomer() {
        String cpr = "1111111111";
        String name = "Kurt";
        RealCustomer c = new RealCustomer(cpr, name);
        assertNotNull(c);
    }

    @Test
    public void testCreateCustomerAndGetCpr() {
        String cpr = "1111111111";
        String name = "Kurt";
        RealCustomer c = new RealCustomer(cpr, name);
        String res = c.getCpr();
        assertEquals(res, cpr);
    }

    @Test
    public void testCreateCustomerAndGetName() {
        String cpr = "1111111111";
        String name = "Kurt";
        RealCustomer c = new RealCustomer(cpr, name);
        String res = c.getName();
        assertEquals(res, name);
    }

    @Test
    public void testCreateCustomerAndGetName() {
        String cpr = "1111111111";
        String name = "Kurt";
        RealCustomer c = new RealCustomer(cpr, name);
        String res = c.getName();
        assertEquals(res, name);
    }
}
