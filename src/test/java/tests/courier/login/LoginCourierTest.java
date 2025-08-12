package tests.courier.login;

import config.Config;
import data.courier.Courier;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataLoggedIn;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {

    LoginCourierDataRequest loginCourierDataRequest = new LoginCourierDataRequest(Config.getUserLogin(), Config.getUserPassword());
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier(loginCourierDataRequest);
        courier.createCourierRequest();
    }

    @Test
    public void loginCourierRequest() {

        Response response = courier.loginCourierRequest(LoginCourierDataLoggedIn.RESPONSE_SPEC);
        LoginCourierDataLoggedIn loginCourierDataLoggedIn = (LoginCourierDataLoggedIn) courier.extractResponseToObject(response, LoginCourierDataLoggedIn.class);
        courier.successLoginCourierCheck(loginCourierDataLoggedIn);

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }

}
