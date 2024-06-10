package ee.tbogdel.testexercise.testPlan;

import ee.tbogdel.testexercise.utils.Base;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@Log4j2
@Feature("Login functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LoginTestA extends Base {


    @Test
    void testRegisterUser() throws InterruptedException {
        login.registerUser();
    }

    @Test
    void testUn() throws InterruptedException {
        login.unsuccessfulLoginWithInvalidPassword();
    }
}
