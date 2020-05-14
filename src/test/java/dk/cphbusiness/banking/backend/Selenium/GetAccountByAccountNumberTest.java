package dk.cphbusiness.banking.backend.Selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GetAccountByAccountNumberTest {
    private static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Before
    public void setUp() {
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void test() {
        driver.get("http://localhost:5000/");
        driver.manage().window().setSize(new Dimension(971, 1020));
        driver.findElement(By.cssSelector(".get-account-container:nth-child(4) .input_container > .svelte-n2exwy")).sendKeys("1111111111");
        driver.findElement(By.cssSelector(".get-account-container:nth-child(4) .svelte-10g3g0h")).click();
        assertThat(driver.findElement(By.cssSelector("tbody th:nth-child(1)")).getText(), is("$ 100"));
        assertThat(driver.findElement(By.cssSelector("tbody th:nth-child(2)")).getText(), is("1111111111"));
        assertThat(driver.findElement(By.cssSelector("tbody th:nth-child(3)")).getText(), is("Danske Bank"));
        assertThat(driver.findElement(By.cssSelector("tbody th:nth-child(4)")).getText(), is("12345678"));
    }
}
