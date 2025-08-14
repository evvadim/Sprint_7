package data.orders;

import config.Config;
import data.orders.get.pagebypage.GetOrdersDataSuccess;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetOrdersPageByPage {

    public GetOrdersPageByPage() {
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .build();

    @Step("Send default GET Request without options")
    public Response getOrderRequest() {
        return given()
                .spec(requestSpecification)
                .get(Config.getGetOrdersEndpoint());
    }

    @Step("Not Null Response for Get Order Request Check")
    public void successOrderResponseCheck(GetOrdersDataSuccess getOrdersDataSuccess) {
        assertThat(getOrdersDataSuccess.getOrders(), GetOrdersDataSuccess.EXPECTED_NOT_NULL);
    }

}
