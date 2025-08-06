package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataCreated;
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
        response.then().spec(CreateCourierDataCreated.responseSpec);

        CreateCourierDataCreated createCourierDataCreated = response.body().as(CreateCourierDataCreated.class);
        assertEquals(CreateCourierDataCreated.unexpectedOkErrorMessage, CreateCourierDataCreated.expectedOk, createCourierDataCreated.isOk());

    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }
}
