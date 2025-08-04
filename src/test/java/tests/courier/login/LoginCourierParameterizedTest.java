package tests.courier.login;

import config.Config;
import data.courier.Courier;
import data.courier.create.CreateCourierDataCreated;
import data.courier.login.LoginCourierDataBadRequest;
import data.courier.login.LoginCourierDataNotFound;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataLoggedIn;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginCourierParameterizedTest {

    private final LoginCourierDataRequest loginCourierWithValidData = new LoginCourierDataRequest(Config.getUserLogin(), Config.getUserPassword());
    private Courier courier;

    // переменные параметризации
    private final LoginCourierDataRequest loginCourierDataRequest;
    private final boolean isCourierShouldBeLoggedIn;
    private final boolean isIncompleteLoginData;

    public LoginCourierParameterizedTest(String login,
                                         String password,
                                         boolean isCourierShouldBeLoggedIn,
                                         boolean isIncompleteLoginData) {

        this.isCourierShouldBeLoggedIn = isCourierShouldBeLoggedIn;
        this.isIncompleteLoginData = isIncompleteLoginData;
        this.loginCourierDataRequest = new LoginCourierDataRequest(login, password);

    }

    @Parameterized.Parameters(name = "Testing Data for Login Courier")
    public static Object[][] getLoginData() {
        return new Object[][] {
//                {Config.getUserLogin(), null, false, true},
                {Config.getUserLogin(), "", false, true},
                {null, Config.getUserPassword(), false, true},
                {Config.getUserLogin(), Config.getWrongUserPassword(), false, false},
                {Config.getWrongUserLogin(), Config.getUserPassword(), false, false},
                {Config.getWrongUserLogin(), Config.getWrongUserPassword(), false, false},
        };
    }

    @Before
    public void setUp() {

        // проверяем возможность создания курьера с валидными тестовыми данными

        // пытаемся создать курьера
        courier = new Courier(loginCourierWithValidData);
        Response response = courier.createCourierRequest();

        // проверяем создался ли курьер
        response.then().spec(CreateCourierDataCreated.responseSpec);
        CreateCourierDataCreated createCourierDataCreated = response.body().as(CreateCourierDataCreated.class);
        assertEquals(CreateCourierDataCreated.unexpectedOkErrorMessage, CreateCourierDataCreated.expectedOk, createCourierDataCreated.getOk());

        // удаляем созданного курьера
//        courier.deleteCourierRequest();

    }

    @Test
    @DisplayName("Negative Login Courier test.")
    public void loginCourierWithData() {

        Courier loginCourierParamData = new Courier(loginCourierDataRequest);
        Response response = loginCourierParamData.loginCourierRequest();

        if (isCourierShouldBeLoggedIn) {
            response.then().spec(LoginCourierDataLoggedIn.responseSpec);
            LoginCourierDataLoggedIn loginCourierDataLoggedIn = response.body().as(LoginCourierDataLoggedIn.class);
            assertThat(LoginCourierDataLoggedIn.unexpectedNotNullErrorMessage, loginCourierDataLoggedIn.getId(), LoginCourierDataLoggedIn.expectedNotNull);
        } else if (isIncompleteLoginData) {
            response.then().spec(LoginCourierDataBadRequest.responseSpec);
            LoginCourierDataBadRequest loginCourierDataBadRequest = response.then().extract().as(LoginCourierDataBadRequest.class);
            assertEquals(LoginCourierDataBadRequest.unexpectedErrorMessage, LoginCourierDataBadRequest.expectedMessage, loginCourierDataBadRequest.getMessage());
        } else {
            response.then().spec(LoginCourierDataNotFound.responseSpec);
            LoginCourierDataNotFound loginCourierDataNotFound = response.then().extract().as(LoginCourierDataNotFound.class);
            assertEquals(LoginCourierDataNotFound.unexpectedErrorMessage, LoginCourierDataNotFound.expectedMessage, loginCourierDataNotFound.getMessage());
        }

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }
}
