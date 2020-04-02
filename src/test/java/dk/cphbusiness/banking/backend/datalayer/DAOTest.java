package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;

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
        System.out.println("After");
        var conn = DBConnector.connection("");
        var statement = conn.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS test");
        conn.close();
    }

    @Test
    public void testGetAccount() throws Exception {
        var DAO = new DAO(dbName);
        var acc = DAO.getAccount("0000000000");
        assertNotNull(acc);
    }
}
