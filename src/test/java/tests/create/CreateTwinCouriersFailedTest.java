package tests.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataConflict;
import data.courier.create.CreateCourierDataResponse;
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
        response.then().spec(CreateCourierDataResponse.responseSpec);

        CreateCourierDataResponse createCourierDataResponse = response.body().as(CreateCourierDataResponse.class);
        assertEquals(CreateCourierDataResponse.unexpectedOkErrorMessage, CreateCourierDataResponse.expectedOk, createCourierDataResponse.getOk());

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
