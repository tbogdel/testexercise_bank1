package ee.tbogdel.testexercise.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenu {

    public final WebDriver driver;
    private final WebDriverWait wait10Sec;
    private final Base base;

    public MainMenu (WebDriver webDriver, WebDriverWait wait){
        driver = webDriver;
        wait10Sec = wait;
        base = new Base(driver, wait10Sec);
    }

    // Labels and values

    //Locators
    By openNewAccountLink = By.xpath("//div[@id='mainPanel'] //li/a[text()='Products']");

    public void openProductsView() {
        base.waitForButtonClickableAndClick(openNewAccountLink);
        base.waitForElementInvisibility(openNewAccountLink);
        assertTrue(driver.getCurrentUrl().contains("products"));

    }

}
