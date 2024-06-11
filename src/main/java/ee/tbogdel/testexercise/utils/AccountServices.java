package ee.tbogdel.testexercise.utils;

import io.qameta.allure.Step;
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

    // Labels and values
    private String openNewAccountLinkValue = "Open New Account";
    private String openNewAccountButtonValue = "Open New Account";
    private String openNewAccountTextOneValue = "What type of Account would you like to open?";
    private String openNewAccountTextTwoValue = "A minimum of $100.00 must be deposited into this account at time of opening. " +
            "Please choose an existing account to transfer funds into the new account.";
    private String openAccountResultViewTitleValue = "Account Opened!";
    private String openAccountViewTitleValue = "Open New Account";
    private String openAccountResultViewTextOneValue = "Congratulations, your account is now open.";
    private String openAccountResultViewTextTwoValue = "Your new account number:";

    //Locators
    private By openNewAccountLink = By.xpath("//div[@id='leftPanel'] //li/a[text()='Open New Account']");
    private By openNewAccountTextOne = By.xpath("//div[@id='rightPanel'] //form//p[1]");
    private By openNewAccountTextTwo = By.xpath("//div[@id='rightPanel'] //form//p[2]");
    private By openAccountResultViewTitle = By.xpath("//div[@id='openAccountResult'] //h1[@class='title']");
    private By openAccountResultViewTextOne = By.xpath("//div[@id='openAccountResult'] //p[contains(text(), 'Congratulations')]");
    private By openAccountResultViewTextTwo = By.xpath("//div[@id='openAccountResult'] //b");
    private By fromAccountIdDropdown = By.xpath("//select[@id='fromAccountId']");
    private By newAccountId = By.xpath("//a[@id='newAccountId']");
    private By accountTypeDropdown = By.xpath("//select[@id='type']");
    private By openNewAccountButton = By.xpath("//div[@id='openAccountForm'] //input[@type='button']");
    private By openAccountViewTitle = By.xpath("//div[@id='openAccountForm'] //h1[@class='title']");


    @Step ("Open Account for Savings and Create account")
    public void openNewAccountForSavings() {

        //Click on option 'Open New Account'
        base.labelVerification(openNewAccountLink, openNewAccountLinkValue);
        base.waitForButtonClickableAndClick(openNewAccountLink);

        //Verify 'Open New Account' view is opened
        base.labelVerification(openAccountViewTitle, openAccountViewTitleValue);

        //Verify all texts in view and Select Savings from dropdown
        base.labelVerification(openNewAccountTextOne, openNewAccountTextOneValue);
        base.dropdownSelect(accountTypeDropdown,"SAVINGS");
        base.getDropdownValue(accountTypeDropdown);

        base.labelVerification(openNewAccountTextTwo, openNewAccountTextTwoValue);
        base.getDropdownValue(fromAccountIdDropdown);

        //Press on 'Open new account' button
        base.labelVerification(openNewAccountButton, openNewAccountButtonValue);
        base.waitForButtonClickableAndClick(openNewAccountButton);

        //Verify operation result and text in opened view
        base.labelVerification(openAccountResultViewTitle, openAccountResultViewTitleValue);
        base.labelVerification(openAccountResultViewTextOne, openAccountResultViewTextOneValue);
        base.labelVerification(openAccountResultViewTextTwo, openAccountResultViewTextTwoValue);
        System.out.println("newAccountId = " + driver.findElement(newAccountId).getText());

    }

}
