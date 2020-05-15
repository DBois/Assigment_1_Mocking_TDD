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
        driver.findElement(By.cssSelector(".svelte-1j6zdsv > .input_container > .svelte-n2exwy")).sendKeys(cpr);
        driver.findElement(By.cssSelector(".svelte-1j6zdsv .svelte-10g3g0h")).click();

        // Assert
        assertThat(driver.findElement(By.cssSelector("tbody > tr:nth-child(1) > th:nth-child(3)")).getText(), is("6666666666"));
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(3)")).getText(), is("7777777777"));
    }

    @Test
    public void testGetCustomerAccountsWithInvalidCPR() {
        // Assemble
        var cpr = "1234560000";

        // Act
        driver.findElement(By.cssSelector(".svelte-1j6zdsv > .input_container > .svelte-n2exwy")).sendKeys(cpr);
        driver.findElement(By.cssSelector(".svelte-1j6zdsv .svelte-10g3g0h")).click();

        // Assert
        assertEquals("Accounts not found for the given id", driver.findElement(By.cssSelector(".exception-handler > .svelte-1t47y6r")).getText());
    }
}
