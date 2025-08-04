package tests.order.create;

import config.Config;
import data.orders.Order;
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

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    Order order;
    Integer track;
    private final CreateOrderDataRequest createOrderDataRequest;

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
        order = new Order(createOrderDataRequest);
    }

    @Test
    public void createOrder() {

        Response response = order.createOrderRequest();
        response.then().spec(CreateOrderDataCreated.responseSpec);

        CreateOrderDataCreated createOrderDataCreated = response.body().as(CreateOrderDataCreated.class);
        track = createOrderDataCreated.getTrack();

        assertThat(CreateOrderDataCreated.unexpectedOkErrorMessage, createOrderDataCreated.getTrack(), CreateOrderDataCreated.expectedNotNull);

    }

    @After
    public void tearDown() {
        order.cancelOrderRequest(track);
    }

}
