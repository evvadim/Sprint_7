package tests.order.getbytrack;

import config.Config;
import data.orders.CancelOrderData;
import data.orders.CreateOrder;
import data.orders.GetOrderByTrack;
import data.orders.create.CreateOrderDataRequest;
import data.orders.create.CreateOrderDataSuccess;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.orders.CancelOrderRequest;

import java.util.List;

public class GetOrderByTrackTest {

    private Integer track;
    
    @Before
    public void setUp() {
        
        // создадим заказ
        CreateOrderDataRequest createOrderDataRequest = new CreateOrderDataRequest(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                List.of(Config.getScooterColorGrey()));

        CreateOrder createOrder = new CreateOrder(createOrderDataRequest);

        Response response = createOrder.createOrderRequest();
        CreateOrderDataSuccess createOrderDataSuccess = response.body().as(CreateOrderDataSuccess.class);

        // извлекаем из ответа данные поля `track`
        track = createOrderDataSuccess.getTrack();

    }

    @Test
    @DisplayName("Get Order by exist `track` number is Success")
    public void getOrderByTrackSuccessTest() {

        GetOrderByTrack getOrderByTrack = new GetOrderByTrack(track);
        Response response = getOrderByTrack.getOrderByTrackRequest();

        GetOrderByTrackDataSuccess getOrderByTrackDataSuccess = response.body().as(GetOrderByTrackDataSuccess.class);
        getOrderByTrack.successOrderResponseCheck(getOrderByTrackDataSuccess);

    }

    @After
    public void tearDown() {

        // отменяем созданный заказ
        new CancelOrderRequest(new CancelOrderData(track)).cancelOrderRequest();

    }
}
