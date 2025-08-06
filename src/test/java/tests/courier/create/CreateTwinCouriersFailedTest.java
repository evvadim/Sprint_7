package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataConflict;
import data.courier.create.CreateCourierDataCreated;
import data.courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateTwinCouriersFailedTest {

    CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier(createCourierDataRequest);

        Response response = courier.createCourierRequest();
        response.then().spec(CreateCourierDataCreated.responseSpec);

        CreateCourierDataCreated createCourierDataCreated = response.body().as(CreateCourierDataCreated.class);
        assertEquals(CreateCourierDataCreated.unexpectedOkErrorMessage, CreateCourierDataCreated.expectedOk, createCourierDataCreated.isOk());

    }

    @Test
    @DisplayName("Negative Twin Couriers Create test")
    public void createTwinCouriersFailed() {

        Courier twinCourier = new Courier(createCourierDataRequest);
        Response response = twinCourier.createCourierRequest();
        response.then().spec(CreateCourierDataConflict.responseSpec);
        CreateCourierDataConflict createCourierDataConflict = response.body().as(CreateCourierDataConflict.class);

        assertEquals(CreateCourierDataConflict.unexpectedErrorMessage, CreateCourierDataConflict.expectedMessage, createCourierDataConflict.getMessage());
//        assertEquals(CreateCourierDataConflict.unexpectedErrorMessage, "Этот логин уже используется. Попробуйте другой.", createCourierDataConflict.getMessage());

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }

}
