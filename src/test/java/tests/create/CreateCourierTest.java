package tests.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataResponse;
import data.courier.Courier;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCourierTest {

    CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier(createCourierDataRequest);
    }

    @Test
    public void createCourierValidData() {

        Response response = courier.createCourierRequest();
        response.then().spec(CreateCourierDataResponse.responseSpec);

        CreateCourierDataResponse createCourierDataResponse = response.body().as(CreateCourierDataResponse.class);
        assertEquals(CreateCourierDataResponse.unexpectedOkErrorMessage, CreateCourierDataResponse.expectedOk, createCourierDataResponse.getOk());

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }
}
