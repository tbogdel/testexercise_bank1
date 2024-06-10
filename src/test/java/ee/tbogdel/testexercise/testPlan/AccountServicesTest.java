package ee.tbogdel.testexercise.testPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import javax.lang.model.SourceVersion;

public class AccountServicesTest extends LoginTest {

    @Order(1)
    @Test
    public void openNewAccountForSavings() throws InterruptedException {

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

        registerUser();

        labelVerification(openNewAccountLink, openNewAccountLinkValue);
        waitForButtonClickableAndClick(openNewAccountLink);

        labelVerification(openAccountViewTitle, openAccountViewTitleValue);

        labelVerification(openNewAccountTextOne, openNewAccountTextOneValue);
        dropdownSelect(accountTypeDropdown,"SAVINGS");
        getDropdownValue(accountTypeDropdown);

        labelVerification(openNewAccountTextTwo, openNewAccountTextTwoValue);
        getDropdownValue(fromAccountIdDropdown);

        labelVerification(openNewAccountButton, openNewAccountButtonValue);
        waitForButtonClickableAndClick(openNewAccountButton);

        labelVerification(openAccountResultViewTitle, openAccountResultViewTitleValue);
        labelVerification(openAccountResultViewTextOne, openAccountResultViewTextOneValue);
        labelVerification(openAccountResultViewTextTwo, openAccountResultViewTextTwoValue);
        System.out.println("newAccountId = " + driver.findElement(newAccountId).getText());

    }

}
