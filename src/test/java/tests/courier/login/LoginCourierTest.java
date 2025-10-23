package tests.courier.login;

import config.Config;
import data.courier.CourierData;
import data.courier.LoginCourierData;
import data.courier.login.LoginCourierDataSuccess;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.courier.CommonCourierRequest;
import requests.courier.CreateCourierRequest;
import requests.courier.DeleteCourierRequest;
import requests.courier.LoginCourierRequest;

public class LoginCourierTest {

    LoginCourierData loginCourierData = new LoginCourierData(Config.getUserLogin(), Config.getUserPassword());
    CourierData courierData;

    @Before
    public void setUp() {
        courierData = new CourierData(loginCourierData);
        new CreateCourierRequest(courierData).createCourierRequest();
    }

    @Test
    public void loginCourierRequest() {

        LoginCourierRequest loginCourierRequest = new LoginCourierRequest(courierData);
        Response response = loginCourierRequest.loginCourierRequest(LoginCourierDataSuccess.RESPONSE_SPEC);
        LoginCourierDataSuccess loginCourierDataSuccess = (LoginCourierDataSuccess) CommonCourierRequest.extractResponseToObject(response, LoginCourierDataSuccess.class);
        loginCourierRequest.successLoginCourierCheck(loginCourierDataSuccess);

    }

    @After
    public void tearDown() {
        new DeleteCourierRequest(courierData).deleteCourierRequest();
    }

}
