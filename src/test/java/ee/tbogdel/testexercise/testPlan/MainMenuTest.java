package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Feature("Main menu functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MainMenuTest extends Base {
    @Story("As a user, I want to open Products from main menu")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void openProductsView() throws InterruptedException {
        login.navigateToLoginPage();
        mainMenu.openProductsView();
    }
}
