package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataBadRequest;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataSuccess;
import data.courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateCourierParameterizedTest {

    private final CreateCourierDataRequest createCourierWithValidData = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    private Courier createCourierParamData;

    // переменные для параметризации
    private final CreateCourierDataRequest createCourierDataRequest;
    private final boolean isCourierShouldBeCreated;

    public CreateCourierParameterizedTest(String login, String password, String firstName, boolean isCourierShouldBeCreated) {
        this.isCourierShouldBeCreated = isCourierShouldBeCreated;
        this.createCourierDataRequest = new CreateCourierDataRequest(login, password, firstName);
    }

    @Parameterized.Parameters(name = "Testing Data for CreateCourier. Set {index}.")
    public static Object[][] getCourierData() {
        return new Object[][] {
                {Config.getUserLogin(), null, Config.getUserFirstName(), false},
                {null, Config.getUserPassword(), Config.getUserFirstName(), false},
                {Config.getUserLogin(), Config.getUserPassword(), null, true},
        };
    }

    @Before
    public void setUp() {

        // проверяем возможность создания курьера с валидными тестовыми данными

        // пытаемся создать курьера
        Courier courier = new Courier(createCourierWithValidData);
        courier.createCourierRequest();

        // удаляем созданного курьера
        // для этого сначала логинимся чтобы получить `id`
        courier.loginCourierRequest();
        courier.deleteCourierRequest();

    }

    @Test
    @DisplayName("Create Courier test. Positive with required fields, Negative without one of required fields.")
    public void createCourierWithData() {

        createCourierParamData = new Courier(createCourierDataRequest);

        if (isCourierShouldBeCreated) {
            createCourierParamData.createCourierRequest(CreateCourierDataSuccess.RESPONSE_SPEC);
        } else {
            createCourierParamData.createCourierRequest(CreateCourierDataBadRequest.RESPONSE_SPEC);
        }

    }

    @After
    public void tearDown() {
        if (isCourierShouldBeCreated) {
            createCourierParamData.loginCourierRequest();
            createCourierParamData.deleteCourierRequest();
        }
    }

}
