package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;


import dk.cphbusiness.banking.backend.models.RealCustomer;
import dk.cphbusiness.banking.backend.doubles.ClockStub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    private static String dbName = "test";

    @BeforeAll
    public static void setupBefore() throws IOException, SQLException {
        System.out.println("Before");
        createTestDatabase();
    }

    @BeforeEach
    public void setupBeforeEach() throws IOException, SQLException {
        System.out.println("Before each");
        createTables(dbName);
        populateDatabase(dbName);
    }


    @AfterAll
    public static void dropDatabase() throws IOException, SQLException {
        deleteDatabase();
    }

    @Test
    public void testGetAccount() throws Exception {
        var DAO = new DAO(dbName);
        var accountNumber = "0000000000";
        var acc = DAO.getAccount(accountNumber);
        assertNotNull(acc);
        assertEquals(accountNumber, acc.getNumber());
    }

    @Test
    public void testGetCustomer() throws Exception {
        var DAO = new DAO(dbName);
        var cpr = "1234560004";
        var customer = DAO.getCustomer(cpr);
        assertNotNull(customer);
        assertEquals(cpr, customer.getCpr());

    }

    @Test
    public void testUpdateCustomer() throws Exception {
        var DAO = new DAO(dbName);
        var customer = DAO.getCustomer("1234560001");
        var newCustomer = new RealCustomer(customer.getCpr(), "Emilio");
        var updatedCustomer = DAO.updateCustomer(newCustomer);

        assertNotNull(updatedCustomer);
        assertEquals(customer.getCpr(), updatedCustomer.getCpr());
        assertEquals(updatedCustomer.getName(), newCustomer.getName());
    }

    @Test
    public void testTransfer() throws Exception {
        //Assert
        var DAO = new DAO(dbName);
        var source = DAO.getAccount("0000000000");
        var target = DAO.getAccount("1111111111");
        var time = new ClockStub().getTime();
        var amount = 1000L;

        //Act
        source.transfer(amount, target, time);
        var actual = DAO.transfer(source,target, time);


        //Assert
        assertNotNull(actual);
        assertEquals(source, actual.getSource());
        assertEquals(target, actual.getTarget());
        assertEquals(time, actual.getTime());
        assertEquals(-amount, actual.getAmount());
    }

    @Test
    public void testGetAccountsFromCustomer() throws Exception {
        String cpr = "1234560001";
        String accountNumber1 = "0000000000";
        String accountNumber2 = "1111111111";
        var expectedAccountNumbers = new ArrayList<String>() {{
            add(accountNumber1);
            add(accountNumber2);
        }};
        var expectedSize = 2;

        var DAO = new DAO(dbName);
        var accounts = DAO.getAccountsFromCustomer(cpr);
        
        assertNotNull(accounts);
        assertEquals(expectedSize, accounts.size());
        for (var i = 0; i < accounts.size(); i++) {
            var currAccount = accounts.get(i);
            assertNotNull(currAccount);
            assertTrue(expectedAccountNumbers.contains(accounts.get(i).getNumber()));
        }
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        var DAO = new DAO(dbName);
        var cpr = "1234560001";
        var customer = DAO.getCustomer(cpr);
        var accounts = DAO.getAccountsFromCustomer(cpr);

        assertNotNull(customer);
        assertEquals(2, accounts.size());

        DAO.deleteCustomer(cpr);
        customer = DAO.getCustomer(cpr);
        accounts = DAO.getAccountsFromCustomer(cpr);

        assertNull(customer);
        assertEquals(0, accounts.size());

    }

}
