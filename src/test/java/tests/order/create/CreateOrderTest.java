package tests.order.create;

import config.Config;
import data.courier.create.CreateCourierDataCreated;
import data.order.Order;
import data.order.create.CreateOrderDataCreated;
import data.order.create.CreateOrderDataRequest;
import data.order.create.scooter.Color;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateOrderTest {

    CreateOrderDataRequest createOrderDataRequest = new CreateOrderDataRequest(Config.getOrderFirstName(),
            Config.getOrderLastName(),
            Config.getOrderAddress(),
            Config.getOrderMetroStation(),
            Config.getOrderPhone(),
            Config.getOrderRentTime(),
            Config.getOrderDeliveryDate(),
            Config.getOrderComment(),
            List.of(new Color(Config.getScooterColorBlack())));

    Order order;
    Integer track;

    @Before
    public void setUp() {
        order = new Order(createOrderDataRequest);
    }

    @Test
    public void createOrder() {

        Response response = order.createOrderRequest();
        response.then().spec(CreateCourierDataCreated.responseSpec);

        CreateOrderDataCreated createOrderDataCreated = response.body().as(CreateOrderDataCreated.class);
        track = createOrderDataCreated.getTrack();

        assertThat(CreateOrderDataCreated.unexpectedOkErrorMessage, createOrderDataCreated.getTrack(), CreateOrderDataCreated.expectedNotNull);

    }

    @After
    public void tearDown() {
        order.cancelOrderRequest(track);
    }

}
