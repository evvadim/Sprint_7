package tests.order.get;

import requests.orders.GetOrdersPageByPageRequest;
import data.orders.get.pagebypage.GetOrdersPageByPageDataSuccess;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersPageByPageTest {

    GetOrdersPageByPageRequest getOrdersPageByPageRequest;

    @Before
    public void setUp() {
        getOrdersPageByPageRequest = new GetOrdersPageByPageRequest();
    }

    @Test
    @DisplayName("Default Get Orders Request. Status line check")
    public void getOrdersTestStatusLineCheck() {

        Response response = getOrdersPageByPageRequest.getOrdersRequest();
        response.then().spec(GetOrdersPageByPageDataSuccess.RESPONSE_SPEC);

    }

    @Test
    @DisplayName("Default Get Orders Request. Body check")
    public void getOrdersTestBodyCheck() {

        Response response = getOrdersPageByPageRequest.getOrdersRequest();

        GetOrdersPageByPageDataSuccess getOrdersPageByPageDataSuccess = response.body().as(GetOrdersPageByPageDataSuccess.class);
        getOrdersPageByPageRequest.successOrderResponseCheck(getOrdersPageByPageDataSuccess);

    }

}
