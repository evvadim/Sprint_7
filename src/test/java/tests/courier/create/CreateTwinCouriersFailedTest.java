package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataConflict;
import data.courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateTwinCouriersFailedTest {

    CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    Courier courier;
    Courier twinCourier;

    @Before
    public void setUp() {
        courier = new Courier(createCourierDataRequest);
        courier.createCourierRequest();
    }

    @Test
    @DisplayName("Negative Twin Couriers Create test")
    public void createTwinCouriersFailed() {

        twinCourier = new Courier(createCourierDataRequest);
        twinCourier.createCourierRequest(CreateCourierDataConflict.RESPONSE_SPECIFICATION);

    }

    @After
    public void tearDown() {

        courier.loginCourierRequest();
        courier.deleteCourierRequest();

        twinCourier.loginCourierRequest();
        twinCourier.deleteCourierRequest();

    }

}
