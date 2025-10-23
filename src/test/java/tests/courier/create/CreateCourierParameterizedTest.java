package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataBadRequest;
import data.courier.CreateCourierData;
import data.courier.create.CreateCourierDataSuccess;
import data.courier.CourierData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.courier.DeleteCourierRequest;

@RunWith(Parameterized.class)
public class CreateCourierParameterizedTest {

    private final CreateCourierData createCourierWithValidData = new CreateCourierData(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    private CourierData courierDataParamData;

    // переменные для параметризации
    private final CreateCourierData createCourierData;
    private final boolean isCourierShouldBeCreated;

    public CreateCourierParameterizedTest(String login, String password, String firstName, boolean isCourierShouldBeCreated) {
        this.isCourierShouldBeCreated = isCourierShouldBeCreated;
        this.createCourierData = new CreateCourierData(login, password, firstName);
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
        CourierData courierData = new CourierData(createCourierWithValidData);
        courierData.createCourierRequest();

        // удаляем созданного курьера
        // для этого сначала логинимся чтобы получить `id`
        courierData.loginCourierRequest();
        new DeleteCourierRequest(courierData).deleteCourierRequest();

    }

    @Test
    @DisplayName("Create Courier test. Positive with required fields, Negative without one of required fields.")
    public void createCourierWithData() {

        courierDataParamData = new CourierData(createCourierData);

        if (isCourierShouldBeCreated) {
            courierDataParamData.createCourierRequest(CreateCourierDataSuccess.RESPONSE_SPEC);
        } else {
            courierDataParamData.createCourierRequest(CreateCourierDataBadRequest.RESPONSE_SPEC);
        }

    }

    @After
    public void tearDown() {
        if (isCourierShouldBeCreated) {
            courierDataParamData.loginCourierRequest();
            new DeleteCourierRequest(courierDataParamData).deleteCourierRequest();
        }
    }

}
