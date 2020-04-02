package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;


import dk.cphbusiness.banking.backend.models.RealCustomer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
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
        var acc = DAO.getAccount("0000000000");
        assertNotNull(acc);
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
}
