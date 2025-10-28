package tests.courier.create;

import config.Config;
import data.courier.CreateCourierData;
import data.courier.create.CreateCourierDataConflict;
import data.courier.CourierData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.courier.CreateCourierRequest;
import requests.courier.DeleteCourierRequest;
import requests.courier.LoginCourierRequest;

public class CreateTwinCouriersFailedTest {

    CreateCourierData createCourierData = new CreateCourierData(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    CourierData courierData;
    CourierData twinCourierData;

    @Before
    public void setUp() {
        courierData = new CourierData(createCourierData);
        new CreateCourierRequest(courierData).createCourierRequest();
    }

    @Test
    @DisplayName("Negative Twin Couriers Create test")
    public void createTwinCouriersFailed() {

        twinCourierData = new CourierData(createCourierData);
        new CreateCourierRequest(twinCourierData).createCourierRequest(CreateCourierDataConflict.RESPONSE_SPECIFICATION);

    }

    @After
    public void tearDown() {

        LoginCourierRequest loginCourierRequest = new LoginCourierRequest(courierData);
        loginCourierRequest.loginCourierRequest();
        courierData.setId(loginCourierRequest.getId());
        new DeleteCourierRequest(courierData).deleteCourierRequest();

        LoginCourierRequest loginTwinCourierRequest = new LoginCourierRequest(twinCourierData);
        loginTwinCourierRequest.loginCourierRequest();
        twinCourierData.setId(loginTwinCourierRequest.getId());
        new DeleteCourierRequest(twinCourierData).deleteCourierRequest();

    }

}
