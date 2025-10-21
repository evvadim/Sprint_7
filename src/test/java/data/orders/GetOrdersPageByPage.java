package data.orders;

import config.Config;
import config.endpoints.Endpoints;
import data.orders.get.pagebypage.GetOrdersPageByPageDataSuccess;
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
    public Response getOrdersRequest() {
        return given()
                .spec(requestSpecification)
                .get(Endpoints.GET_ORDERS);
    }

    @Step("Send GET Request with `page` & `limit` options")
    public Response getOrdersRequest(Integer limit, Integer page) {
        return given()
                .spec(requestSpecification)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .get(Endpoints.GET_ORDERS);
    }

    @Step("Not Null Response for Get Order Request Check")
    public void successOrderResponseCheck(GetOrdersPageByPageDataSuccess getOrdersPageByPageDataSuccess) {
        assertThat(getOrdersPageByPageDataSuccess.getOrders(), GetOrdersPageByPageDataSuccess.EXPECTED_NOT_NULL);
    }

}
