package ee.tbogdel.testexercise.testPlan;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    public WebDriver driver;
    private String loginPageURL = "https://parabank.parasoft.com/parabank/index.htm";
    private String invalidUsername = "invalidUsername";
    private String invalidPassword = "invalidPassword";
    private String unsuccessfulLoginError = "Error!";


    // Locators
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@type='submit' and @value='Log In']");
    private By unsuccessfulLoginErrorMessage = By.xpath("//div[@id='rightPanel']/h1[@class='title']");
    private WebDriverWait wait10Sec;


    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WebDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait10Sec = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void unsuccessfulLogin() throws InterruptedException {

        navigateToLoginPage();

        waitForInputFieldAndFillText(usernameField, invalidUsername);

        waitForInputFieldAndFillText(passwordField, invalidPassword);

        waitForButtonClickableAndClick(loginButton);

        String expected = unsuccessfulLoginError;
        String actual;

        actual = driver.findElement(unsuccessfulLoginErrorMessage).getText();
        wait10Sec.withMessage(actual);
        actual = actual.trim();
        String message = String.format("Expected: %s, Actual: %s", expected, actual);
        Assert.isTrue(actual.equals(expected), message);
    }

    private void waitForButtonClickableAndClick(By button) {
        wait10Sec.until(ExpectedConditions.elementToBeClickable(button));
        driver.findElement(button).click();
    }

    private void waitForInputFieldAndFillText(By usernameField, String invalidUsername) {
        wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usernameField));
        driver.findElement(usernameField).sendKeys(invalidUsername);
    }

    private void navigateToLoginPage() {
        driver.get(loginPageURL);
    }


    @AfterEach
    public void cleanup(){
        driver.quit();
        driver = null;
    }

}
