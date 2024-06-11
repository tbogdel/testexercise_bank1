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

import java.time.Duration;


public class Base {

    public WebDriver driver;
    public WebDriverWait wait10Sec;

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

    @Step("Label verification for text {1}")
    void labelVerification(By locator, String textExpected) {

        String actual;
        try {
            wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            screenshot();
            e.printStackTrace();
            Assert.fail("TEST FAILED: Expected element not found " + locator + " " + textExpected);
        }
    }

    @Step ("Wait for button {0} to be clickable and click")
    void waitForButtonClickableAndClick(By button) {
        try {
            wait10Sec.until(ExpectedConditions.elementToBeClickable(button));
            driver.findElement(button).click();
        } catch (Exception e) {
            screenshot();
            e.printStackTrace();
            Assert.fail("TEST FAILED: Expected element not found or not clickable " + button);
        }
    }

    @Step("Wait for element to be invisible")
    void waitForElementInvisibility(By locator) {
        try {
            wait10Sec.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            screenshot();
            e.printStackTrace();
            Assert.fail("TEST FAILED: Element is visible " + locator);
        }
    }

    @Step("Wait for input field {1} to be visible and fill in")
    void waitForInputFieldAndFillText(By locator, String fieldName) {
        try {
            wait10Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            driver.findElement(locator).sendKeys(fieldName);
        } catch (Exception e) {
            screenshot();
            e.printStackTrace();
            Assert.fail("TEST FAILED: Field not found " + locator);
        }
    }

    @Step("Select option {1} from dropdown element")
    void dropdownSelect(By locator, String value) {
        try {
            WebElement dropdown = wait10Sec.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(dropdown);
            select.selectByVisibleText(value);

        } catch (Exception e) {
            screenshot();
            e.printStackTrace();
            Assert.fail("TEST FAILED: Element not found " + locator);
        }
    }

    @Step("Get dropdown value")
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
