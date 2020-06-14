package dk.cphbusiness.banking.backend.Selenium;

// Generated by Selenium IDE
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static dk.cphbusiness.banking.backend.settings.Settings.DB_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GetCustomerByCPRTest {
    private static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    private static String dbName = DB_NAME;


    @Before
    public void setUp() throws IOException, SQLException {
        createTestDatabase();
        createTables(dbName);
        populateDatabase(dbName);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:5000/");
        driver.manage().window().setSize(new Dimension(1920, 994));

        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

    }

    @After
    public void close(){
        driver.quit();
    }



    @Test
    public void getCustomerByCPR() {
        //Assemble
        var cpr = "1234560001";
        var expectedName = "Emil";
        var expectedAccounts = "0000000000, 1111111111";

        //Act
        driver.findElement(By.name("getcustomer-cpr"));
        driver.findElement(By.name("getcustomer-cpr")).sendKeys(cpr);
        driver.findElement(By.name("getcustomer-submit")).click();

        //Assert
        assertThat(driver.findElement(By.name("getcustomer-resname")).getText(), is(expectedName));
        assertThat(driver.findElement(By.name("getcustomer-rescpr")).getText(), is(cpr));
        assertThat(driver.findElement(By.name("getcustomer-resnumbers")).getText(), is(expectedAccounts));
    }

    @Test
    public void getCustomerByInvalidCPR(){
        //Assemble
        var cpr = "abc";

        //Act
        driver.findElement(By.name("getcustomer-cpr"));
        driver.findElement(By.name("getcustomer-cpr")).sendKeys(cpr);
        driver.findElement(By.name("getcustomer-submit")).click();

        //Assert
        assertThat(driver.findElement(By.name("exceptionHolder")).getText(), is("Customer does not exist"));
    }

}
