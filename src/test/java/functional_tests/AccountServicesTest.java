package functional_tests;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Feature("Account services functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AccountServicesTest extends Base {

    @Story("As a user, I want to open account for savings")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(1)
    void testOpenAccountForSavings() throws InterruptedException {
        login.registerUser();
        accountServices.openNewAccountForSavings();
    }
}
