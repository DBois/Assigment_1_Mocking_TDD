package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static dk.cphbusiness.banking.contract.AccountManager.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static dk.cphbusiness.banking.backend.settings.Settings.DB_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AccountRestTest {
    private static String dbName = DB_NAME;
    private static final String URI = "http://localhost:8081/accounts/";
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
    public void testAccountDoesntExist()
            throws IOException {
        // Given
        var id = 1;
        HttpUriRequest request = new HttpGet(URI + id);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void testGetAccount()
            throws IOException {

        // Given
        String expectedAccountNumber = "0000000000";
        HttpUriRequest request = new HttpGet(URI + expectedAccountNumber);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        var jsonResponse = EntityUtils.toString(httpResponse.getEntity());
        var result = GSON.fromJson(jsonResponse, AccountDetail.class);
        var expectedCustomer = "Emil";

        // Then
        System.out.println(jsonResponse);
        assertThat(httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
        assertEquals(expectedCustomer, result.getCustomer().getName());
        assertEquals(expectedAccountNumber, result.getNumber());

    }
}
