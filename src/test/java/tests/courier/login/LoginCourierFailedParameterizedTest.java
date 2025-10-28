package tests.courier.login;

import config.Config;
import data.courier.CourierData;
import data.courier.login.LoginCourierDataBadRequest;
import data.courier.login.LoginCourierDataNotFound;
import data.courier.LoginCourierData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.courier.CreateCourierRequest;
import requests.courier.DeleteCourierRequest;
import requests.courier.LoginCourierRequest;

@RunWith(Parameterized.class)
public class LoginCourierFailedParameterizedTest {

    private final LoginCourierData loginCourierWithValidData = new LoginCourierData(Config.getUserLogin(), Config.getUserPassword());
    private CourierData courierData;

    // переменные параметризации
    private final LoginCourierData loginCourierData;

    public LoginCourierFailedParameterizedTest(String login,
                                               String password) {

        this.loginCourierData = new LoginCourierData(login, password);

    }

    @Parameterized.Parameters(name = "Testing Data for Login Courier")
    public static Object[][] getLoginData() {
        return new Object[][] {
                {Config.getUserLogin(), null},
                {null, Config.getUserPassword()},
                {Config.getUserLogin(), Config.getWrongUserPassword()},
                {Config.getWrongUserLogin(), Config.getUserPassword()},
                {Config.getWrongUserLogin(), Config.getWrongUserPassword()},
        };
    }

    @Before
    public void setUp() {

        // проверяем возможность создания курьера с валидными тестовыми данными

        // пытаемся создать курьера
        courierData = new CourierData(loginCourierWithValidData);
        new CreateCourierRequest(courierData).createCourierRequest();

    }

    @Test
    @DisplayName("Negative Login Courier test.")
    public void loginCourierWithData() {

        CourierData loginCourierDataParamData = new CourierData(loginCourierData);
        boolean isIncompleteLoginData = (loginCourierData.getLogin() == null) || (loginCourierData.getPassword() == null) || (loginCourierData.getPassword().isEmpty());

        LoginCourierRequest loginCourierRequest = new LoginCourierRequest(loginCourierDataParamData);

        if (isIncompleteLoginData) {
            loginCourierRequest.loginCourierRequest(LoginCourierDataBadRequest.RESPONSE_SPEC);
        } else {
            loginCourierRequest.loginCourierRequest(LoginCourierDataNotFound.RESPONSE_SPEC);
        }

    }

    @After
    public void tearDown() {
        LoginCourierRequest loginCourierRequest = new LoginCourierRequest(courierData);
        loginCourierRequest.loginCourierRequest();
        courierData.setId(loginCourierRequest.getId());
        new DeleteCourierRequest(courierData).deleteCourierRequest();
    }
}
