package tests.courier.login;

import config.Config;
import data.courier.Courier;
import data.courier.login.LoginCourierDataBadRequest;
import data.courier.login.LoginCourierDataNotFound;
import data.courier.login.LoginCourierDataRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LoginCourierParameterizedTest {

    private final LoginCourierDataRequest loginCourierWithValidData = new LoginCourierDataRequest(Config.getUserLogin(), Config.getUserPassword());
    private Courier courier;

    // переменные параметризации
    private final LoginCourierDataRequest loginCourierDataRequest;

    public LoginCourierParameterizedTest(String login,
                                         String password) {

        this.loginCourierDataRequest = new LoginCourierDataRequest(login, password);

    }

    @Parameterized.Parameters(name = "Testing Data for Login Courier")
    public static Object[][] getLoginData() {
        return new Object[][] {
//                {Config.getUserLogin(), null},
                {Config.getUserLogin(), ""},
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
        courier = new Courier(loginCourierWithValidData);
        courier.createCourierRequest();

    }

    @Test
    @DisplayName("Negative Login Courier test.")
    public void loginCourierWithData() {

        Courier loginCourierParamData = new Courier(loginCourierDataRequest);
//        boolean isIncompleteLoginData = (loginCourierDataRequest.getLogin() == null) || (loginCourierDataRequest.getPassword() == null);
        boolean isIncompleteLoginData = (loginCourierDataRequest.getLogin() == null) || (loginCourierDataRequest.getPassword() == null) || (loginCourierDataRequest.getPassword().isEmpty());

        if (isIncompleteLoginData) {
            loginCourierParamData.loginCourierRequest(LoginCourierDataBadRequest.RESPONSE_SPEC);
        } else {
            loginCourierParamData.loginCourierRequest(LoginCourierDataNotFound.RESPONSE_SPEC);
        }

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }
}
