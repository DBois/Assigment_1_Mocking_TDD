package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;


import dk.cphbusiness.banking.backend.doubles.BankDummy;
import dk.cphbusiness.banking.backend.doubles.ClockStub;
import dk.cphbusiness.banking.backend.doubles.CustomerDummy;
import dk.cphbusiness.banking.backend.models.*;
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
        var accountNumber = "0000000000";
        var acc = DAO.getAccount(accountNumber);
        assertNotNull(acc);
        assertEquals(accountNumber, acc.getNumber());
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
        assertEquals(actual.getSource(), source);
        assertEquals(actual.getTarget(), target);
        assertEquals(actual.getTime(), time);
        assertEquals(actual.getAmount(), amount);
    }

}
