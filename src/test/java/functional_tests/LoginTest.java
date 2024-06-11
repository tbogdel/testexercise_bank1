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


@Feature("Login functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LoginTest extends Base {

    @Story("As a user, I want to be able to login and Logout from system")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    @Order(1)
    void testSuccessfulLogin() {
        login.successfulLogin();
        login.userLogout();
    }

    @Story("As a user, I want to be able to login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Order(2)
    void testUnsuccessfulLoginWithInvalidUsername() {
        login.unsuccessfulLoginWithInvalidUsername();
    }

    @Story("As a user, I want to be able to login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Order(3)
    void testUnsuccessfulLoginWithInvalidPassword() {
        login.unsuccessfulLoginWithInvalidPassword();
    }


    @Story("As a user, I want to make sign up")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(4)
    void testRegisterUser() {
        login.registerUser();
    }
}
