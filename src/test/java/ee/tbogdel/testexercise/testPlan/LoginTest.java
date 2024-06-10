package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@Feature("Login functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LoginTest extends Base {

    @Story("As a user, I want to be able to login and Logout from system")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("User login")
    @Test()
    void successfulLogin() throws InterruptedException {
        login.successfulLogin();
        login.userLogout();
    }

    @Story("As a user, I want to be able to login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void unsuccessfulLoginWithInvalidUsername() throws InterruptedException {
        login.unsuccessfulLoginWithInvalidUsername();
    }

    @Story("As a user, I want to be able to login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void unsuccessfulLoginWithInvalidPassword() throws InterruptedException {
        login.unsuccessfulLoginWithInvalidPassword();
    }


    @Story("As a user, I want to open login page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void testRegisterUser() throws InterruptedException {
        login.registerUser();
    }
}
