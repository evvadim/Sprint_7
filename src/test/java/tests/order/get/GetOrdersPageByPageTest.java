package tests.order.get;

import data.orders.GetOrdersPageByPage;
import data.orders.get.pagebypage.GetOrdersPageByPageDataSuccess;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersPageByPageTest {

    GetOrdersPageByPage getOrdersPageByPage;

    @Before
    public void setUp() {
        getOrdersPageByPage = new GetOrdersPageByPage();
    }

    @Test
    @DisplayName("Default Get Orders Request. Status line check")
    public void getOrdersTestStatusLineCheck() {

        Response response = getOrdersPageByPage.getOrdersRequest();
        response.then().spec(GetOrdersPageByPageDataSuccess.RESPONSE_SPEC);

    }

    @Test
    @DisplayName("Default Get Orders Request. Body check")
    public void getOrdersTestBodyCheck() {

        Response response = getOrdersPageByPage.getOrdersRequest();

        GetOrdersPageByPageDataSuccess getOrdersPageByPageDataSuccess = response.body().as(GetOrdersPageByPageDataSuccess.class);
        getOrdersPageByPage.successOrderResponseCheck(getOrdersPageByPageDataSuccess);

    }

}
