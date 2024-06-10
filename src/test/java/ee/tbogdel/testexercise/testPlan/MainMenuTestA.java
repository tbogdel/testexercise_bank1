package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Feature("Login functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MainMenuTestA extends Base {
    @Test
    void openProductsView() throws InterruptedException {
        login.navigateToLoginPage();
        mainMenu.openProductsView();
    }
}
