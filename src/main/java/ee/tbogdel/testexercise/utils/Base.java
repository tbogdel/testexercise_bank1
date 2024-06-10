package ee.tbogdel.testexercise.utils;

import org.testng.Assert;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

import java.time.Duration;


public class Base {

    public WebDriver driver;
    public WebDriverWait wait10Sec;
    private static final Logger logger = Logger.getLogger(Base.class.getName());

    public Login login;
    public MainMenu mainMenu;
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
        this.mainMenu = new MainMenu(driver, wait10Sec);
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
        } catch (Exception e) {
            screenshot();
            Assert.fail("TEST FAILED: Expected element not found " + locator + " " + textExpected);
        }
    }

    void waitForButtonClickableAndClick(By button) {
        try {
            wait10Sec.until(ExpectedConditions.elementToBeClickable(button));
            driver.findElement(button).click();
        } catch (Exception e) {
            screenshot();
            Assert.fail("TEST FAILED: Expected element not found or not clickable " + button);
        }
    }

    void waitForElementInvisibility(By locator) {
        try {
            wait10Sec.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            screenshot();
            Assert.fail("TEST FAILED: Element is visible " + locator);
        }
    }

    void waitForInputFieldAndFillText(By locator, String fieldName) {
        try {
            wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            driver.findElement(locator).sendKeys(fieldName);
        } catch (Exception e) {
            screenshot();
            Assert.fail("TEST FAILED: Field not found " + locator);
        }
    }

    void dropdownSelect(By locator, String value) {
        try {
            WebElement dropdown = wait10Sec.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(dropdown);
            select.selectByVisibleText(value);

        } catch (Exception e) {
            screenshot();
            Assert.fail("TEST FAILED: Element not found " + locator);
        }
    }

    public String getDropdownValue(By locator) {
        WebElement dropdown = wait10Sec.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    @Step("User takes a screenshot.")
    @Attachment
    public byte[] screenshot() {
        return ((TakesScreenshot) this.driver)
                .getScreenshotAs(OutputType.BYTES);
    }

}
