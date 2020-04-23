package dk.cphbusiness.banking.backend.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static dk.cphbusiness.banking.contract.AccountManager.*;

import com.google.gson.reflect.TypeToken;
import dk.cphbusiness.banking.contract.CustomerManager;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static dk.cphbusiness.banking.backend.settings.Settings.DB_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerRestTest {
    private static String dbName = DB_NAME;
    private static final String URI = "http://localhost:8081/customers";
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

//    @Test
//    public void testGetCustomer()
//            throws IOException {
//
//        // Assemble
//        var expectedName = "Emil";
//        var expectedCPR = "123456001";
//        HttpUriRequest request = new HttpGet(URI + expectedCPR);
//        var httpResponse = HttpClientBuilder.create().build().execute(request);
//        var jsonResponse = EntityUtils.toString(httpResponse.getEntity());
//
//        // Act
//        var result = GSON.fromJson(jsonResponse, CustomerManager.CustomerDetail.class);
//
//        // Assert
//        assertThat(httpResponse.getStatusLine().getStatusCode(),
//                equalTo(HttpStatus.SC_OK));
//        assertEquals(expectedName, result.getName());
//        assertEquals(expectedCPR, result.getCpr());
//    }
//
//


//    @Test
//    public void testUpdateCustomer() throws IOException {
//        // Assemble
//        var expectedName = "It works!";
//        var CPR = "123456001";
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(URI + "/customer");
//
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("cpr", "12345601"));
//        params.add(new BasicNameValuePair("name", expectedName));
//        httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//        //Act
//        CloseableHttpResponse response = client.execute(httpPost);
//        var jsonResponse = EntityUtils.toString(response.getEntity());
//        var result = GSON.fromJson(jsonResponse, CustomerManager.CustomerDetail.class);
//
//        // Assert
//        assertThat(response.getStatusLine().getStatusCode(),
//                equalTo(HttpStatus.SC_OK));
//        assertEquals(expectedName, result.getName());
//        assertEquals(CPR, result.getCpr());
//
//
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
//        client.close();
//    }

    @Test
    public void testUpdateCustomer() throws ClientProtocolException, IOException {
        // Assemble
        var expectedName = "It works!";
        var CPR = "123456001";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");
        String json = "{\"cpr\":" + CPR + " , \"name\":" + expectedName + "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // Act
        CloseableHttpResponse response = client.execute(httpPost);
        var jsonResponse = EntityUtils.toString(response.getEntity());
        var result = GSON.fromJson(jsonResponse, CustomerManager.CustomerDetail.class);

        // Assert
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        assertEquals(expectedName, result.getName());
        assertEquals(CPR, result.getCpr());
        client.close();
    }


}
