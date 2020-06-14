package dk.cphbusiness.banking.backend.datalayer;


import dk.cphbusiness.banking.backend.models.RealCustomer;
import dk.cphbusiness.banking.backend.doubles.ClockStub;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static org.junit.jupiter.api.Assertions.*;
import static dk.cphbusiness.banking.backend.settings.Settings.*;

public class DAOTest {

    private static String dbName = DB_NAME;

    @BeforeAll
    public static void setupBefore() throws IOException, SQLException {
        createTestDatabase();
    }

    @BeforeEach
    public void setupBeforeEach() throws IOException, SQLException {
        createTables(dbName);
        populateDatabase(dbName);
    }


    @Test
    public void testGetAccount() throws Exception {
        //Assemble
        var DAO = new DAO();
        var accountNumber = "0000000000";

        //Act
        var acc = DAO.getAccount(accountNumber);

        //Assert
        assertNotNull(acc);
        assertEquals(accountNumber, acc.getNumber());
    }

    @Test
    public void testGetCustomer() throws Exception {
        //Assemble
        var DAO = new DAO();
        var cpr = "1234560004";

        //Act
        var customer = DAO.getCustomer(cpr);

        //Assert
        assertNotNull(customer);
        assertEquals(cpr, customer.getCpr());

    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //Assemble
        var DAO = new DAO();
        var customer = DAO.getCustomer("1234560001");
        var newCustomer = new RealCustomer(customer.getCpr(), "Emilio");

        //Act
        var updatedCustomer = DAO.updateCustomer(newCustomer);

        //Assert
        assertNotNull(updatedCustomer);
        assertEquals(customer.getCpr(), updatedCustomer.getCpr());
        assertEquals(updatedCustomer.getName(), newCustomer.getName());
    }

    @Test
    public void testTransfer() throws Exception {
        //Assemble
        var DAO = new DAO();
        var source = DAO.getAccount("0000000000");
        var target = DAO.getAccount("1111111111");
        var time = new ClockStub().getTime();
        var amount = 1000L;

        //Act
        source.transfer(amount, target, time);
        var actual = DAO.transfer(source, target, time);


        //Assert
        assertNotNull(actual);
        assertEquals(source.getNumber(), actual.getSource());
        assertEquals(target.getNumber(), actual.getTarget());
        assertEquals(time, actual.getTime());
        assertEquals(amount, actual.getAmount());
    }

    @Test
    public void testGetAccountsFromCustomer() throws Exception {
        //Assemble
        var DAO = new DAO();
        String cpr = "1234560001";
        String accountNumber1 = "0000000000";
        String accountNumber2 = "1111111111";
        var expectedAccountNumbers = new ArrayList<String>() {{
            add(accountNumber1);
            add(accountNumber2);
        }};
        var expectedSize = 2;

        //Act
        var accounts = DAO.getAccountsFromCustomer(cpr);

        //Assert
        assertNotNull(accounts);
        assertEquals(expectedSize, accounts.size());
        for (var i = 0; i < accounts.size(); i++) {
            var currAccount = accounts.get(i);
            assertNotNull(currAccount);
            assertTrue(expectedAccountNumbers.contains(currAccount.getNumber()));
        }
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        var DAO = new DAO();
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

    @Test
    public void getMovements() throws Exception {
        //Assemble
        var DAO = new DAO();
        var source = DAO.getAccount("0000000000");
        var target = DAO.getAccount("1111111111");
        var time = new ClockStub().getTime();
        var amount = 1000L;
        for (int i = 0; i < 5; i++) {
            source.transfer(amount, target, time);
            DAO.transfer(source, target, time);
        }

        //Act
        var movementsSource = DAO.getMovements("0000000000");
        var movementsTarget = DAO.getMovements("1111111111");


        //Assert
        assertNotNull(movementsSource);
        assertEquals(5, movementsSource.size());
        assertEquals(5, movementsTarget.size());
    }

}
