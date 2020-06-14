package dk.cphbusiness.banking.backend.datalayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection singleton;
    private static String currentDbName;

    public static Connection connection(String databaseName) throws IOException, SQLException {
        var url = "jdbc:postgresql://localhost:5432/" + databaseName;
        if(singleton == null || !currentDbName.equals(databaseName) || singleton.isClosed()){
            currentDbName = databaseName;
            String filePath = System.getenv("ABSOLUTE_PATH");
            filePath += "\\gorilla.txt";
            String password = Files.readString(Paths.get(filePath));
            singleton = DriverManager.getConnection(url, "postgres", password);
        }
        return singleton;
    }

}
