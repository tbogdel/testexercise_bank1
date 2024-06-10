package ee.tbogdel.testexercise.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static com.sun.activation.registries.LogSupport.log;


public class Login {

    public final WebDriver driver;
    private final WebDriverWait wait10Sec;

    private final Base base;

    public Login(WebDriver webDriver, WebDriverWait wait){
        driver = webDriver;
        wait10Sec = wait;
        base = new Base(webDriver, wait10Sec);

    }


    private static final Logger logger = Logger.getLogger(Login.class.getName());
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


    @Step ("User login with valid credentials")
    public void successfulLogin() throws InterruptedException {

        String welcomeUserName = "Welcome" + customerFirstname + customerLastname;

        navigateToLoginPage();
        base.waitForInputFieldAndFillText(usernameInput, validUsername);
        base.waitForInputFieldAndFillText(passwordInput, validPassword);
        base.waitForButtonClickableAndClick(loginButton);
        //assertTrue(driver.getCurrentUrl().contains("overview"));
        base.labelVerification(welcomeLabel, welcomeUserName);
        base.labelVerification(accountsOverviewViewTitle, accountsOverview);

    }

    @Step ("User log out")
    public void userLogout() throws InterruptedException {

        base.waitForButtonClickableAndClick(logoutButton);
        base.labelVerification(customerLoginLabel, customerLoginText);
        base.labelVerification(loginButton, login);

    }

    @Step ("User login with invalid username")
    public void unsuccessfulLoginWithInvalidUsername() throws InterruptedException {

        navigateToLoginPage();
        log("Error happened");
        base.waitForInputFieldAndFillText(usernameInput, invalidUsername);
        base.waitForInputFieldAndFillText(passwordInput, validPassword);
        base.waitForButtonClickableAndClick(loginButton);
        base.labelVerification(unsuccessfulLoginErrorMessage, unsuccessfulLoginError);

    }

    @Step ("User login with invalid password")
    public void unsuccessfulLoginWithInvalidPassword() throws InterruptedException {

        navigateToLoginPage();
        base.waitForInputFieldAndFillText(usernameInput, validUsername);
        base.waitForInputFieldAndFillText(passwordInput, invalidPassword);
        base.waitForButtonClickableAndClick(loginButton);
        base.labelVerification(unsuccessfulLoginErrorMessage, unsuccessfulLoginError);

    }

    @Step ("New user registration")
    public void registerUser() throws InterruptedException {

        navigateToLoginPage();
        base.waitForButtonClickableAndClick(customerSignupButton);
        base.labelVerification(customerSignupViewTitle, customerSignupViewTitleValue);
        base.labelVerification(customerSignupViewText, customerSignupViewTextValue);

        base.waitForInputFieldAndFillText(registerFirstnameInput, customerFirstname);
        base.waitForInputFieldAndFillText(registerLastnameInput, customerLastname);
        base.waitForInputFieldAndFillText(registerStreetInput, customerStreet);
        base.waitForInputFieldAndFillText(registerCityInput, customerCity);
        base.waitForInputFieldAndFillText(registerStateInput, customerState);
        base.waitForInputFieldAndFillText(registerZipCodeInput, customerZipCode);
        base.waitForInputFieldAndFillText(registerSsnInput, customerSsn);

        String newUser = UsernameGenerator.generateUsername(8);
        base.waitForInputFieldAndFillText(registerUsernameInput, newUser);

        base.waitForInputFieldAndFillText(registerPasswordInput, validPassword);
        base.waitForInputFieldAndFillText(registerRepeatedPasswordInput, validPassword);
        base.waitForButtonClickableAndClick(customerSignupSubmitButton);

        base.labelVerification(customerSignupSuccessfulViewTitle, newUser);
        base.labelVerification(customerSignupSuccessfulViewText, customerSignupSuccessfulViewTextValue);
        //TODO Add Welcome First Last verification

    }

    void navigateToLoginPage() {
        driver.get(loginPageURL);
    }


}
