package dk.cphbusiness.banking.backend.datalayer;
import org.junit.*;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static org.junit.jupiter.api.Assertions.*;


public class DatabaseTest {

    private static String dbName = "test";

    @Test
    public void testConnection() throws IOException, SQLException {
        Connection conn = DBConnector.connection("");
        assertNotNull(conn);
        conn.close();
    }

    @Test
    public void testCreateDatabase() throws IOException, SQLException {
        // Set up
        createTestDatabase();
        createTables(dbName);
        var conn = DBConnector.connection(dbName);
        var statement = conn.createStatement();

        // Get table names
        statement.execute("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE';");

        var resultsSet = statement.getResultSet();
        var tableNames = new ArrayList<String>();
        while(resultsSet.next()){
            tableNames.add(resultsSet.getString(1));
        }

        assertTrue(tableNames.contains("customer"));
        assertTrue(tableNames.contains("account"));
        assertTrue(tableNames.contains("bank"));
        assertTrue(tableNames.contains("movement"));
        assertEquals(tableNames.size(), 4);
        conn.close();
        deleteDatabase();
    }

    @Test
    public void testDeleteDatabase() throws IOException, SQLException {
        createTestDatabase();
        createTables(dbName);
        populateDatabase(dbName);
        deleteDatabase();
        assertThrows(PSQLException.class, () -> {
            DBConnector.connection(dbName);
        });
    }
}
