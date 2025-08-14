package data.orders;

import config.Config;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetOrderByTrack {

    private Integer t;

    public GetOrderByTrack(Integer t) {
        this.t = t;
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .build();

    public Response getOrderByTrackRequest() {
        return given()
                .spec(requestSpecification)
                .queryParam("t", t)
                .get(Config.getGetOrderByTrackEndpoint());
    }

    @Step("Not Null Response for Get Order By Track Request Check")
    public void successOrderResponseCheck(GetOrderByTrackDataSuccess getOrderByTrackDataSuccess) {
        assertThat(getOrderByTrackDataSuccess.getOrder(), GetOrderByTrackDataSuccess.EXPECTED_NOT_NULL);
    }

    public Integer getT() {
        return t;
    }
}
