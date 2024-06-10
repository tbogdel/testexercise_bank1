package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@Log4j2
@Feature("Account services functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AccountServicesTest extends Base {

    @Story("As a user, I want to open account for savings")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void testA() throws InterruptedException {
        login.registerUser();
        accountServices.openNewAccountForSavings();
    }
}
