package ee.tbogdel.testexercise.testPlan;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    public WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    }

    @Test
    public void unsuccessfulLogin() throws InterruptedException {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("invalidUsername");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("invalidPassword");
        driver.findElement(By.xpath("//input[@type='submit' and @value='Log In']")).click();
        Thread.sleep(5000);

        String expected = "Error!";
        String actual;

        actual = driver.findElement(By.xpath("//div[@id='rightPanel']/h1[@class='title']")).getText();
        wait.withMessage(actual);
        actual = actual.trim();
        String message = String.format("Expected: %s, Actual: %s", expected, actual);
        Assert.isTrue(actual.equals(expected), message);

    }



    @AfterEach
    public void cleanup(){
        driver.quit();
        driver = null;
    }

}
