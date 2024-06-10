package ee.tbogdel.testexercise.utils;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountServices {

    public final WebDriver driver;
    private final WebDriverWait wait10Sec;
    private final Base base;

    public AccountServices (WebDriver webDriver, WebDriverWait wait){
        driver = webDriver;
        wait10Sec = wait;
        base = new Base(driver, wait10Sec);
    }

    @Step ("Open new Account for Savings and Create account")
    public void openNewAccountForSavings() throws InterruptedException {

        // Labels and values
        String openNewAccountLinkValue = "Open New Account";
        String openNewAccountButtonValue = "Open New Account";
        String openNewAccountTextOneValue = "What type of Account would you like to open?";
        String openNewAccountTextTwoValue = "A minimum of $100.00 must be deposited into this account at time of opening. " +
                "Please choose an existing account to transfer funds into the new account.";
        String openAccountResultViewTitleValue = "Account Opened!";
        String openAccountViewTitleValue = "Open New Account";
        String openAccountResultViewTextOneValue = "Congratulations, your account is now open.";
        String openAccountResultViewTextTwoValue = "Your new account number:";

        //Locators
        By openNewAccountLink = By.xpath("//div[@id='leftPanel'] //li/a[text()='Open New Account']");
        By openNewAccountTextOne = By.xpath("//div[@id='rightPanel'] //form//p[1]");
        By openNewAccountTextTwo = By.xpath("//div[@id='rightPanel'] //form//p[2]");
        By openAccountResultViewTitle = By.xpath("//div[@id='openAccountResult'] //h1[@class='title']");
        By openAccountResultViewTextOne = By.xpath("//div[@id='openAccountResult'] //p[contains(text(), 'Congratulations')]");
        By openAccountResultViewTextTwo = By.xpath("//div[@id='openAccountResult'] //b");
        By fromAccountIdDropdown = By.xpath("//select[@id='fromAccountId']");
        By newAccountId = By.xpath("//a[@id='newAccountId']");
        By accountTypeDropdown = By.xpath("//select[@id='type']");
        By openNewAccountButton = By.xpath("//div[@id='openAccountForm'] //input[@type='button']");
        By openAccountViewTitle = By.xpath("//div[@id='openAccountForm'] //h1[@class='title']");

        //registerUser();

        base.labelVerification(openNewAccountLink, openNewAccountLinkValue);
        base.waitForButtonClickableAndClick(openNewAccountLink);

        base.labelVerification(openAccountViewTitle, openAccountViewTitleValue);

        base.labelVerification(openNewAccountTextOne, openNewAccountTextOneValue);
        base.dropdownSelect(accountTypeDropdown,"SAVINGS");
        base.getDropdownValue(accountTypeDropdown);

        base.labelVerification(openNewAccountTextTwo, openNewAccountTextTwoValue);
        base.getDropdownValue(fromAccountIdDropdown);

        base.labelVerification(openNewAccountButton, openNewAccountButtonValue);
        base.waitForButtonClickableAndClick(openNewAccountButton);

        base.labelVerification(openAccountResultViewTitle, openAccountResultViewTitleValue);
        base.labelVerification(openAccountResultViewTextOne, openAccountResultViewTextOneValue);
        base.labelVerification(openAccountResultViewTextTwo, openAccountResultViewTextTwoValue);
        System.out.println("newAccountId = " + driver.findElement(newAccountId).getText());

    }

}
