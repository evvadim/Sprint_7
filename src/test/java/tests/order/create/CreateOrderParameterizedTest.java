package tests.order.create;

import config.Config;
import data.orders.CreateOrder;
import data.orders.create.CreateOrderDataCreated;
import data.orders.create.CreateOrderDataRequest;
import data.scooter.ScooterColor;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    CreateOrder createOrder;
    Integer track;
    private final CreateOrderDataRequest createOrderDataRequest;
    Response response;

    public CreateOrderParameterizedTest(List<ScooterColor> scooterColorList) {
        this.createOrderDataRequest = new CreateOrderDataRequest(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                scooterColorList);
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
        createOrder = new CreateOrder(createOrderDataRequest);
    }

    @Test
    public void createOrder() {

        response = createOrder.createOrderRequest();
        response.then().spec(CreateOrderDataCreated.RESPONSE_SPEC);
        CreateOrderDataCreated createOrderDataCreated = response.body().as(CreateOrderDataCreated.class);
        createOrder.successOrderCreatedCheck(createOrderDataCreated);

    }

    @After
    public void tearDown() {

        CreateOrderDataCreated createOrderDataCreated = response.body().as(CreateOrderDataCreated.class);
        track = createOrderDataCreated.getTrack();
        createOrder.cancelOrderRequest(track);

    }

}
