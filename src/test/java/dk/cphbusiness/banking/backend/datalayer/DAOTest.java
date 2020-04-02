package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;
import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;

public class DAOTest {

    private static String dbName = "test";

    @Before
    public void setupBefore() throws IOException, SQLException {
        createTestDatabase();
    }

    @BeforeEach
    public void setupBeforeEach() throws IOException, SQLException {
        createTables(dbName);
        populateDatabase(dbName);
    }


//    @After
//    public void dropDatabase() throws IOException, SQLException {
//        var conn = DBConnector.connection(dbName);
//        var statement = conn.createStatement();
//        statement.executeUpdate("DROP DATABASE IF EXISTS test");
//        conn.close();
//    }

    @Test
    public void testGetAccount() throws Exception {
        var conn = DBConnector.connection(dbName);
        var statement = conn.createStatement();
        var DAO = new DAO(dbName);
        var acc = DAO.getAccount("0000000000");
        assertNotNull(acc);

    }
}
