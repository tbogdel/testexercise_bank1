package ee.tbogdel.testexercise.testPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuTest extends LoginTest{

    @Order(1)
    @Test
    public void openProductsView() throws InterruptedException {

        //Locators
        By openNewAccountLink = By.xpath("//div[@id='mainPanel'] //li/a[text()='Products']");

        navigateToLoginPage();
        waitForButtonClickableAndClick(openNewAccountLink);
        waitForElementInvisibility(openNewAccountLink);
        assertTrue(driver.getCurrentUrl().contains("products"));

    }

}
