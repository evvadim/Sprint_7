package tests.courier.login;

import config.Config;
import data.courier.Courier;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataSuccess;
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

        Response response = courier.loginCourierRequest(LoginCourierDataSuccess.RESPONSE_SPEC);
        LoginCourierDataSuccess loginCourierDataSuccess = (LoginCourierDataSuccess) courier.extractResponseToObject(response, LoginCourierDataSuccess.class);
        courier.successLoginCourierCheck(loginCourierDataSuccess);

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }

}
