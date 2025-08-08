package tests.courier.create;

import config.Config;
import data.courier.create.CreateCourierDataRequest;
import data.courier.create.CreateCourierDataCreated;
import data.courier.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTest {

    CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier(createCourierDataRequest);
    }

    @Test
    public void createCourierValidDataExpectedOk() {
        courier.createCourierRequest(CreateCourierDataCreated.RESPONSE_SPEC);
    }

    @After
    public void tearDown() {
        courier.deleteCourierRequest();
    }
}
