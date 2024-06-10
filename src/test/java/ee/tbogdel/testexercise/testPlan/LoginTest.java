package ee.tbogdel.testexercise.testPlan;

import dev.failsafe.internal.util.Assert;


import ee.tbogdel.testexercise.utils.UsernameGenerator;
import ee.tbogdel.testexercise.utils.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static com.sun.activation.registries.LogSupport.log;


public class LoginTest {

    public WebDriver driver;
        private static final Logger logger = Logger.getLogger(LoginTest.class.getName());
    private String loginPageURL = "https://parabank.parasoft.com/parabank/index.htm";
    private String invalidUsername = "invalidUsername";
    private String invalidPassword = "invalidPassword";
    private String validUsername = "a2";
    private String validPassword = "123";
    private String unsuccessfulLoginError = "Error!";
    private String accountsOverview = "Accounts Overview";
    private String login = "Log In";
    private String customerFirstname = "John";
    private String customerLastname = "Doe";
    private String customerLoginText = "Customer Login";
    private String customerSignupViewTitleValue = "Signing up is easy!";
    private String customerSignupSuccessfulText1 = "Welcome ";
    private String customerSignupViewTextValue = "If you have an account with us you can sign-up for free instant online access. " +
            "You will have to provide some personal information.";
    private String customerSignupSuccessfulViewTextValue = "Your account was created successfully. You are now logged in.";
    private String customerStreet = "customerStreet";
    private String customerCity = "customerCity";
    private String customerState = "customerState";
    private String customerZipCode = "customerZipCode";
    private String customerSsn = "130296-8229";


    // Locators
    private By usernameInput = By.xpath("//input[@name='username']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@type='submit' and @value='Log In']");
    private By logoutButton = By.xpath("//div[@id='leftPanel'] //li/a[text()='Log Out']");
    private By unsuccessfulLoginErrorMessage = By.xpath("//div[@id='rightPanel'] //h1[@class='title']");
    private By welcomeLabel = By.xpath("//div[@id='leftPanel'] //p[@class='smallText' and b[text()='Welcome'] and contains(text(), '\" + firstname + lastname + \"' )]");
    private By customerSignupSuccessfulViewTitle = By.xpath("//div[@id='rightPanel'] //h1[contains(text(), 'Welcome')]");
    private By customerSignupSuccessfulViewText = By.xpath("//div[@id='rightPanel'] //p[contains(text(), 'Your account')]");
    private By accountsOverviewViewTitle = By.xpath("//div[@id='showOverview'] //h1[@class='title']");
    private By customerLoginLabel = By.xpath("//div[@id='leftPanel'] //h2[contains(text(), 'Customer Login')]");
    private By customerSignupButton = By.xpath("//div[@id='leftPanel'] //p/a[text()='Register']");
    private By customerSignupSubmitButton = By.xpath("//div[@id='rightPanel'] //input[@type='submit']");
    private By customerSignupViewTitle = By.xpath("//h1[contains(text(), 'Signing up is easy!')]");
    private By customerSignupViewText = By.xpath("//p[contains(text(), 'If you have')]");
    private By registerFirstnameInput = By.id("customer.firstName");
    private By registerLastnameInput = By.id("customer.lastName");
    private By registerStreetInput = By.id("customer.address.street");
    private By registerCityInput = By.id("customer.address.city");
    private By registerStateInput = By.id("customer.address.state");
    private By registerZipCodeInput = By.id("customer.address.zipCode");
    private By registerSsnInput = By.id("customer.ssn");
    private By registerUsernameInput = By.id("customer.username");
    private By registerPasswordInput = By.id("customer.password");
    private By registerRepeatedPasswordInput = By.id("repeatedPassword");
    private WebDriverWait wait10Sec;


    @BeforeEach
    public void setup(){
        driver = WebDriverManager.initializeWebDriver();
        wait10Sec = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Story("My Story")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "taskID", type = "jira")
    @DisplayName("my Display name")
    @Description("""
            My Description\s

            Line1
            Line2

            """)

    @Order(1)
    @Test
    public void successfulLogin() throws InterruptedException {

        String welcomeUserName = "Welcome" + customerFirstname + customerLastname;

        navigateToLoginPage();
        waitForInputFieldAndFillText(usernameInput, validUsername);
        waitForInputFieldAndFillText(passwordInput, validPassword);
        waitForButtonClickableAndClick(loginButton);
        //assertTrue(driver.getCurrentUrl().contains("overview"));
        labelVerification(welcomeLabel, welcomeUserName);
        labelVerification(accountsOverviewViewTitle, accountsOverview);

    }

    @Order(2)
    @Test
    public void userLogout() throws InterruptedException {

        waitForButtonClickableAndClick(logoutButton);
        labelVerification(customerLoginLabel, customerLoginText);
        labelVerification(loginButton, login);

    }

    @Order(3)
    @Test()
    public void unsuccessfulLoginWithInvalidUsername() throws InterruptedException {

        navigateToLoginPage();
        log("Error happened");
        waitForInputFieldAndFillText(usernameInput, invalidUsername);
        waitForInputFieldAndFillText(passwordInput, validPassword);
        waitForButtonClickableAndClick(loginButton);
        labelVerification(unsuccessfulLoginErrorMessage, unsuccessfulLoginError);

    }

    @Order(3)
    @Test()
    public void unsuccessfulLoginWithInvalidPassword() throws InterruptedException {

        navigateToLoginPage();
        waitForInputFieldAndFillText(usernameInput, validUsername);
        waitForInputFieldAndFillText(passwordInput, invalidPassword);
        waitForButtonClickableAndClick(loginButton);
        labelVerification(unsuccessfulLoginErrorMessage, unsuccessfulLoginError);

    }

    @Order(4)
    @Test()
    public void registerUser() throws InterruptedException {

        navigateToLoginPage();
        waitForButtonClickableAndClick(customerSignupButton);
        labelVerification(customerSignupViewTitle, customerSignupViewTitleValue);
        labelVerification(customerSignupViewText, customerSignupViewTextValue);

        waitForInputFieldAndFillText(registerFirstnameInput, customerFirstname);
        waitForInputFieldAndFillText(registerLastnameInput, customerLastname);
        waitForInputFieldAndFillText(registerStreetInput, customerStreet);
        waitForInputFieldAndFillText(registerCityInput, customerCity);
        waitForInputFieldAndFillText(registerStateInput, customerState);
        waitForInputFieldAndFillText(registerZipCodeInput, customerZipCode);
        waitForInputFieldAndFillText(registerSsnInput, customerSsn);

        String newUser = UsernameGenerator.generateUsername(8);
        waitForInputFieldAndFillText(registerUsernameInput, newUser);

        waitForInputFieldAndFillText(registerPasswordInput, validPassword);
        waitForInputFieldAndFillText(registerRepeatedPasswordInput, validPassword);
        waitForButtonClickableAndClick(customerSignupSubmitButton);

        labelVerification(customerSignupSuccessfulViewTitle, newUser);
        labelVerification(customerSignupSuccessfulViewText, customerSignupSuccessfulViewTextValue);
        //TODO Add Welcome First Last verification

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

    void navigateToLoginPage() {
        driver.get(loginPageURL);
    }


    @AfterEach
    public void cleanup(){
        driver.quit();
        driver = null;
    }

}
