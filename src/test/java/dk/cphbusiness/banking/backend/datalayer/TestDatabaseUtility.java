package dk.cphbusiness.banking.backend.datalayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

// Used to hold utility methods used in test classes
public class TestDatabaseUtility {

    public static void createTestDatabase() throws IOException, SQLException {
        var conn = DBConnector.connection("");
        var statement = conn.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS test");
        statement.executeUpdate("CREATE DATABASE test");
        conn.close();
    }

    public static void deleteDatabase() throws IOException, SQLException {
        var conn = DBConnector.connection("");
        var statement = conn.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS test");
        conn.close();
    }

    public static void populateDatabase(String databaseName) throws IOException, SQLException {
        var conn = DBConnector.connection(databaseName);
        var statement = conn.createStatement();

        var filePath = System.getenv("ABSOLUTE_PATH") + "\\populateScript.sql";
        var sqlString = Files.readString(Paths.get(filePath));

        statement.execute(sqlString);
        conn.close();
    }

    public static void createTables(String databaseName) throws IOException, SQLException {
        var conn = DBConnector.connection(databaseName);
        var statement = conn.createStatement();

        // Read SQL File
        var filePath = System.getenv("ABSOLUTE_PATH") + "\\createTable.sql";
        var sqlString = Files.readString(Paths.get(filePath));

        // Execute SQL file
        statement.execute(sqlString);
        conn.close();
    }
}
