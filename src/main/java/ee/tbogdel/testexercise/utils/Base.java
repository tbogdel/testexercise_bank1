package ee.tbogdel.testexercise.utils;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Base {

    public WebDriver driver;
    public WebDriverWait wait10Sec;
    private static final Logger logger = Logger.getLogger(Base.class.getName());

    public Login login;
    //public MainMenu mainMenu;
    public AccountServices accountServices;


    public Base(WebDriver driver, WebDriverWait wait10Sec) {
        this.driver = driver;
        this.wait10Sec = wait10Sec;
    }

    public Base() {
    }

    @BeforeEach
    public void setup(){
        this.driver = WebDriverManager.initializeWebDriver();
        this.wait10Sec = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.login = new Login(driver, wait10Sec);
        //this.mainMenu = new MainMenu(driver, wait10Sec);
        this.accountServices = new AccountServices(driver, wait10Sec);
    }

    @AfterEach
    public void cleanup(){
        driver.quit();
        driver = null;
    }

    void labelVerification(By locator, String textExpected) {

        String actual;
        try {
            wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (NoSuchElementException e) {
            logger.severe("Text not found or incorrect: " + e.getMessage());
            actual = driver.findElement(locator).getText();
            wait10Sec.withMessage(actual);
            actual = actual.trim();
            String message = String.format("Expected: %s, Actual: %s", textExpected, actual);
            Assert.isTrue(actual.equals(textExpected), message);
            // You can take a screenshot here for debugging purposes
        }
    }

    void waitForButtonClickableAndClick(By button) {
        try {
            wait10Sec.until(ExpectedConditions.elementToBeClickable(button));
            driver.findElement(button).click();
        } catch (NoSuchElementException e) {
            logger.severe("Failed to click button: " + e.getMessage());
            // You can take a screenshot here for debugging purposes
        }
    }

    void waitForElementInvisibility(By locator) {
        try {
            wait10Sec.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            logger.severe("Failed to find element: " + e.getMessage());
            // You can take a screenshot here for debugging purposes
        }
    }

    void waitForInputFieldAndFillText(By locator, String fieldName) {
        try {
            wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            driver.findElement(locator).sendKeys(fieldName);
        } catch (NoSuchElementException e) {
            logger.severe(fieldName + " field not found: " + e.getMessage());
            // You can take a screenshot here for debugging purposes
        }
    }

    void dropdownSelect(By locator, String value) {
        //By selectElement = By.xpath("//select[@id='type']");
        WebElement dropdown = wait10Sec.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public String getDropdownValue(By locator) {
        WebElement dropdown = wait10Sec.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

}
