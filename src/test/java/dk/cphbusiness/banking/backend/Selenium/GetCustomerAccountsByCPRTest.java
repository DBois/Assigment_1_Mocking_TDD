package dk.cphbusiness.banking.backend.Selenium;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static dk.cphbusiness.banking.backend.datalayer.TestDatabaseUtility.*;
import static dk.cphbusiness.banking.backend.settings.Settings.DB_NAME;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetCustomerAccountsByCPRTest {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:5000");
        driver.manage().window().setSize(new Dimension(1920, 994));

        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void close(){
        driver.quit();
    }

    @Test
    public void testGetCustomerAccountsWithValidCPR() {
        // Assemble
        var cpr = "1234560004";

        // Act
        driver.findElement(By.name("getaccounts-cpr")).sendKeys(cpr);
        driver.findElement(By.name("getaccounts-submit")).click();

        // Assert
        assertThat(driver.findElement(By.name("0number")).getText(), is("6666666666"));
        assertThat(driver.findElement(By.name("1number")).getText(), is("7777777777"));
    }

    @Test
    public void testGetCustomerAccountsWithInvalidCPR() {
        // Assemble
        var cpr = "1234560000";

        // Act
        driver.findElement(By.name("getaccounts-cpr")).sendKeys(cpr);
        driver.findElement(By.name("getaccounts-submit")).click();

        // Assert
        // This will fail because the backend doesn't throw an exception to the frontend
        assertEquals("Accounts not found for the given id", driver.findElement(By.name("exceptionHandler")).getText());
    }
}
