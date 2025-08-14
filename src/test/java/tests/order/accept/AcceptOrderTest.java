package tests.order.accept;

import config.Config;
import data.courier.Courier;
import data.courier.create.CreateCourierDataRequest;
import data.orders.AcceptOrder;
import data.orders.CreateOrder;
import data.orders.GetOrderByTrack;
import data.orders.accept.AcceptOrderDataSuccess;
import data.orders.create.CreateOrderDataSuccess;
import data.orders.create.CreateOrderDataRequest;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AcceptOrderTest {

    CreateOrderDataSuccess createOrderDataSuccess;
    Courier courier;
    GetOrderByTrackDataSuccess getOrderByTrackDataSuccess;

    @Before
    public void setUp() {

        // создадим курьера
        CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
        courier = new Courier(createCourierDataRequest);

        // авторизуемся чтобы получить его `id`
        courier.loginCourierRequest();

        // создадим заказ
        CreateOrderDataRequest createOrderDataRequest = new CreateOrderDataRequest(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                List.of(Config.getScooterColorBlack()));

        CreateOrder createOrder = new CreateOrder(createOrderDataRequest);
        createOrderDataSuccess = createOrder.createOrderRequest().body().as(CreateOrderDataSuccess.class);

        // по номеру `track` получим `id` заказа
        GetOrderByTrack getOrderByTrack = new GetOrderByTrack(createOrderDataSuccess.getTrack());
        getOrderByTrackDataSuccess = getOrderByTrack.getOrderByTrackRequest().body().as(GetOrderByTrackDataSuccess.class);

    }

    @Test
    public void acceptOrder() {

        AcceptOrder acceptOrder = new AcceptOrder(getOrderByTrackDataSuccess.getOrder().getId(), courier.getId());
        Response response = acceptOrder.acceptOrderRequest();
        acceptOrder.checkResponseSpecs(response, AcceptOrderDataSuccess.RESPONSE_SPEC);

    }

}
