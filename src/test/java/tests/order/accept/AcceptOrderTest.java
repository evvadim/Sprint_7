package tests.order.accept;

import config.Config;
import data.courier.CourierData;
import data.courier.CreateCourierData;
import data.orders.AcceptOrderData;
import requests.orders.AcceptOrderRequest;
import requests.orders.CreateOrderRequest;
import data.orders.GetOrderByTrackData;
import data.orders.accept.AcceptOrderDataSuccess;
import data.orders.create.CreateOrderDataSuccess;
import data.orders.CreateOrderData;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import requests.orders.GetOrderByTrackRequest;

import java.util.List;

public class AcceptOrderTest {

    CreateOrderDataSuccess createOrderDataSuccess;
    CourierData courierData;
    GetOrderByTrackDataSuccess getOrderByTrackDataSuccess;

    @Before
    public void setUp() {

        // создадим курьера
        CreateCourierData createCourierData = new CreateCourierData(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
        courierData = new CourierData(createCourierData);

        // авторизуемся чтобы получить его `id`
        courierData.loginCourierRequest();

        // создадим заказ
        CreateOrderData createOrderData = new CreateOrderData(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                List.of(Config.getScooterColorBlack()));

        CreateOrderRequest createOrderRequest = new CreateOrderRequest(createOrderData);
        createOrderDataSuccess = createOrderRequest.createOrderRequest().body().as(CreateOrderDataSuccess.class);

        // по номеру `track` получим `id` заказа
        GetOrderByTrackData getOrderByTrackData = new GetOrderByTrackData(createOrderDataSuccess.getTrack());
        GetOrderByTrackRequest getOrderByTrackRequest = new GetOrderByTrackRequest(getOrderByTrackData);
        getOrderByTrackDataSuccess = getOrderByTrackRequest.getOrderByTrackRequest().body().as(GetOrderByTrackDataSuccess.class);
    }

    @Test
    public void acceptOrder() {

        AcceptOrderData acceptOrderData = new AcceptOrderData(getOrderByTrackDataSuccess.getOrder().getId(), courierData.getId());
        AcceptOrderRequest acceptOrderRequest = new AcceptOrderRequest(acceptOrderData);
        Response response = acceptOrderRequest.acceptOrderRequest();
        acceptOrderRequest.checkResponseSpecs(response, AcceptOrderDataSuccess.RESPONSE_SPEC);

    }

}
