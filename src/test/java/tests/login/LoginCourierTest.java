package tests.login;

import config.Config;
import data.courier.Courier;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataResponse;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

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

        Response response = courier.loginCourierRequest();
        response.then().spec(LoginCourierDataResponse.responseSpec);

        LoginCourierDataResponse loginCourierDataResponse = response.body().as(LoginCourierDataResponse.class);
        assertThat(LoginCourierDataResponse.unexpectedNotNullErrorMessage, loginCourierDataResponse.getId(), LoginCourierDataResponse.expectedNotNull);

    }

    @After
    public void tearDown() throws Exception {
        courier.deleteCourierRequest();
    }

}
