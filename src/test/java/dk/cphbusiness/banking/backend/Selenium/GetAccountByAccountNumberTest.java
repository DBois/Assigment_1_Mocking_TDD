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

public class GetAccountByAccountNumberTest {
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
    public void testGetAccountWithValidAccountNumber() {
        // Assemble
        var accountNumber = "1111111111";

        // Act
        driver.findElement(By.name("getaccount-number")).sendKeys(accountNumber);
        driver.findElement(By.name("getaccount-submit")).click();

        // Assert
        assertThat(driver.findElement(By.name("getaccount-resbalance")).getText(), is("$ 100"));
        assertThat(driver.findElement(By.name("getaccount-resnumber")).getText(), is("1111111111"));
        assertThat(driver.findElement(By.name("getaccount-resname")).getText(), is("Danske Bank"));
        assertThat(driver.findElement(By.name("getaccount-rescvr")).getText(), is("12345678"));
    }

    @Test
    public void testGetAccountWithInvalidAccountNumber() {
        // Assemble
        var accountNumber = "1234";

        // Act
        driver.findElement(By.name("getaccount-number")).sendKeys(accountNumber);
        driver.findElement(By.name("getaccount-submit")).click();

        // Assert
        assertEquals("Account not found", driver.findElement(By.name("exceptionHolder")).getText());
    }

}
