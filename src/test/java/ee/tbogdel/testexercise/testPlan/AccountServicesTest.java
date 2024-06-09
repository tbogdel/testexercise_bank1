package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountServicesTest {


    @Order(1)
    @Test
    public void openNewAccountForSavings() throws InterruptedException {

        String openNewAccountLinkValue = "Open New Account";
        String openNewAccountTextOneValue = "What type of Account would you like to open?";

        //Locators
        By openNewAccountLink = By.xpath("//div[@id='leftPanel'] //li/a[text()='Open New Account']");
        By openNewAccountTextOne = By.xpath("//div[@id='rightPanel'] //form//p[1]");


        LoginTest loginTest = new LoginTest();
        loginTest.setup();
        loginTest.registerUser();

        loginTest.waitForButtonClickableAndClick(openNewAccountLink);
        loginTest.labelVerification(openNewAccountLink, openNewAccountLinkValue);
        loginTest.labelVerification(openNewAccountTextOne, openNewAccountTextOneValue);
        loginTest.dropdownSelectB("SAVINGS");
        Thread.sleep(5000);
        loginTest.cleanup();

    }



}
