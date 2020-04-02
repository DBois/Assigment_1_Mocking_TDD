package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DAOTest {

    @Before
    public void setupBefore() throws IOException, SQLException {
        createTestDatabase();
    }

    @BeforeEach
    public void populateDatabase() throws IOException, SQLException {
        var conn = DBConnector.connection("test");
        String filePath = new File("").getAbsolutePath() + "\\populateScript.sql";
        String sqlString = Files.readString(Paths.get(filePath));
        conn.prepareStatement(sqlString);
    }

    @Test
    public void testGetAccount(){

    }
}
