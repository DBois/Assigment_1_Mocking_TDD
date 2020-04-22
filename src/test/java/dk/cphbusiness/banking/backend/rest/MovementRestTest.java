package dk.cphbusiness.banking.backend.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.banking.backend.facade.AccountFacade;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static dk.cphbusiness.banking.backend.settings.Settings.DB_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MovementRestTest {
    private static String dbName = DB_NAME;
    private static final String URI = "http://localhost:8081/movements/";
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeAll
    public static void setupBefore() throws IOException, SQLException {
        createTestDatabase();
    }

    @BeforeEach
    public void setupBeforeEach() throws IOException, SQLException {
        createTables(dbName);
        populateDatabase(dbName);
    }

    @Test
    public void testGetMovementsFromAccount() throws Exception {
        // Given
        var sourceNumber = "6666666666";
        var targetNumber = "5555555555";
        HttpUriRequest request = new HttpGet(URI + sourceNumber);

        AccountFacade af = new AccountFacade();
        for (int i = 1; i < 6; i++) {
            af.transfer(i*1000, sourceNumber, targetNumber);
        }

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        var jsonResponse = EntityUtils.toString(httpResponse.getEntity());
        var listType = new TypeToken<ArrayList<MovementDetail>>(){}.getType();
        List<MovementDetail> result = GSON.fromJson(jsonResponse, listType);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertEquals(5, result.size());

    }
}
