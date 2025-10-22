package tests.order.getbytrack;

import config.Config;
import data.orders.CancelOrderData;
import requests.orders.CreateOrderRequest;
import data.orders.GetOrderByTrackData;
import data.orders.CreateOrderData;
import data.orders.create.CreateOrderDataSuccess;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.orders.CancelOrderRequest;
import requests.orders.GetOrderByTrackRequest;

import java.util.List;

public class GetOrderByTrackTest {

    private Integer track;
    
    @Before
    public void setUp() {
        
        // создадим заказ
        CreateOrderData createOrderData = new CreateOrderData(Config.getOrderFirstName(),
                Config.getOrderLastName(),
                Config.getOrderAddress(),
                Config.getOrderMetroStation(),
                Config.getOrderPhone(),
                Config.getOrderRentTime(),
                Config.getOrderDeliveryDate(),
                Config.getOrderComment(),
                List.of(Config.getScooterColorGrey()));

        CreateOrderRequest createOrderRequest = new CreateOrderRequest(createOrderData);

        Response response = createOrderRequest.createOrderRequest();
        CreateOrderDataSuccess createOrderDataSuccess = response.body().as(CreateOrderDataSuccess.class);

        // извлекаем из ответа данные поля `track`
        track = createOrderDataSuccess.getTrack();

    }

    @Test
    @DisplayName("Get Order by exist `track` number is Success")
    public void getOrderByTrackSuccessTest() {

        GetOrderByTrackData getOrderByTrackData = new GetOrderByTrackData(track);
        GetOrderByTrackRequest getOrderByTrackRequest = new GetOrderByTrackRequest(getOrderByTrackData);
        Response response = getOrderByTrackRequest.getOrderByTrackRequest();

        GetOrderByTrackDataSuccess getOrderByTrackDataSuccess = response.body().as(GetOrderByTrackDataSuccess.class);
        getOrderByTrackRequest.successOrderResponseCheck(getOrderByTrackDataSuccess);
    }

    @After
    public void tearDown() {

        // отменяем созданный заказ
        new CancelOrderRequest(new CancelOrderData(track)).cancelOrderRequest();

    }
}
