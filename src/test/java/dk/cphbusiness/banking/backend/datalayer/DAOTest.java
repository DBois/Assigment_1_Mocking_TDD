package dk.cphbusiness.banking.backend.datalayer;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.createTestDatabase;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;

public class DAOTest {

    @Before
    public void setupBefore() throws IOException, SQLException {
        createTestDatabase();
    }

    @BeforeEach
    public void populateDatabase(){
        
    }
}
