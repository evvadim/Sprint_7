package tests.order.create;

import config.Config;
import data.orders.CancelOrderData;
import requests.orders.CreateOrderRequest;
import data.orders.create.CreateOrderDataSuccess;
import data.orders.CreateOrderData;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.orders.CancelOrderRequest;

import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    CreateOrderRequest createOrderRequest;
    Integer track;
    private final CreateOrderData createOrderData;
    Response response;

    public CreateOrderParameterizedTest(List<String> color) {
        this.createOrderData = new CreateOrderData(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                color);
    }

    @Parameterized.Parameters(name = "Testing Data for Create Order. Set {index}.")
    public static Object[][] getOrderData() {
        return new Object[][] {
                {List.of(Config.getScooterColorBlack())},
                {List.of(Config.getScooterColorGrey())},
                {List.of(Config.getScooterColorBlack(), Config.getScooterColorGrey())},
                {List.of()},
        };
    }

    @Before
    public void setUp() {
        createOrderRequest = new CreateOrderRequest(createOrderData);
    }

    @Test
    public void createOrder() {

        response = createOrderRequest.createOrderRequest();
        response.then().spec(CreateOrderDataSuccess.RESPONSE_SPEC);
        CreateOrderDataSuccess createOrderDataSuccess = response.body().as(CreateOrderDataSuccess.class);
        createOrderRequest.successOrderCreatedCheck(createOrderDataSuccess);

    }

    @After
    public void tearDown() {

        CreateOrderDataSuccess createOrderDataSuccess = response.body().as(CreateOrderDataSuccess.class);
        track = createOrderDataSuccess.getTrack();
        new CancelOrderRequest(new CancelOrderData(track)).cancelOrderRequest();

    }

}
