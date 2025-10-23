package tests.courier.create;

import config.Config;
import data.courier.CreateCourierData;
import data.courier.create.CreateCourierDataSuccess;
import data.courier.CourierData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.courier.CreateCourierRequest;
import requests.courier.DeleteCourierRequest;
import requests.courier.LoginCourierRequest;

public class CreateCourierTest {

    CreateCourierData createCourierData = new CreateCourierData(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    CourierData courierData;

    @Before
    public void setUp() {
        courierData = new CourierData(createCourierData);
    }

    @Test
    public void createCourierValidDataExpectedOk() {
        new CreateCourierRequest(courierData).createCourierRequest(CreateCourierDataSuccess.RESPONSE_SPEC);
    }

    @After
    public void tearDown() {
        new LoginCourierRequest(courierData).loginCourierRequest();
        new DeleteCourierRequest(courierData).deleteCourierRequest();
    }
}
