package dk.cphbusiness.banking.backend.datalayer;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.cphbusiness.banking.backend.datalayer.DBConnector;

import static org.junit.Assert.*;

public class DatabaseTest {


    @Test
    public void testConnection() throws IOException, SQLException {
        Connection conn = DBConnector.connection("");
        assertNotNull(conn);
    }

    @Test
    public void testCreateDatabase() throws IOException, SQLException {
        // Set up
        var conn = DBConnector.connection("");
        var statement = conn.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS test");
        statement.executeUpdate("CREATE DATABASE test");
        conn.close();
        conn = DBConnector.connection("test");
        statement = conn.createStatement();

        // Read SQL File
        String filePath = new File("").getAbsolutePath() + "\\createDatabase.sql";
        String sqlString = Files.readString(Paths.get(filePath));

        // Execute SQL file
        statement.execute(sqlString);
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
    }
}
