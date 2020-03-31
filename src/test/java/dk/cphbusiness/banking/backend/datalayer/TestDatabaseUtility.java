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
        conn = DBConnector.connection("test");
        statement = conn.createStatement();

        // Read SQL File
        String filePath = new File("").getAbsolutePath() + "\\createDatabase.sql";
        String sqlString = Files.readString(Paths.get(filePath));

        // Execute SQL file
        statement.execute(sqlString);
        conn.close();
    }
}
