package tests.order.get;

import data.orders.GetOrders;
import data.orders.get.GetOrdersDataResponse;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersTest {

    GetOrders getOrders;

    @Before
    public void setUp() {
        getOrders = new GetOrders();
    }

    @Test
    @DisplayName("Default Get Orders Request")
    public void getOrdersTest() {

        Response response = getOrders.getOrderRequest();
        response.then().spec(GetOrdersDataResponse.RESPONSE_SPEC);

        GetOrdersDataResponse getOrdersDataResponse = response.body().as(GetOrdersDataResponse.class);
        getOrders.successOrderResponseCheck(getOrdersDataResponse);

    }

}
