package dk.cphbusiness.datalayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static String url = "jdbc:postgresql://localhost:5432/test_bank";
    private static Connection singleton;

    public static Connection connection() throws IOException, SQLException {

        if(singleton == null){
            String filePath = new File("").getAbsolutePath();
            filePath += "\\gorilla.txt";
            System.out.println(filePath);
            String password = Files.readString(Paths.get(filePath));
            singleton = DriverManager.getConnection(url, "postgres", password);
        }
        return singleton;
    }

}
