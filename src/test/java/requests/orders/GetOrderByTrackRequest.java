package requests.orders;

import config.Config;
import config.endpoints.Endpoints;
import data.orders.GetOrderByTrackData;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetOrderByTrackRequest {

    private final Integer t;

    public GetOrderByTrackRequest(GetOrderByTrackData getOrderByTrackData) {
        this.t = getOrderByTrackData.getT();
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .build();

    public Response getOrderByTrackRequest() {
        return given()
                .spec(requestSpecification)
                .queryParam("t", t)
                .get(Endpoints.GET_ORDER_BY_TRACK);
    }

    @Step("Not Null Response for Get Order By Track Request Check")
    public void successOrderResponseCheck(GetOrderByTrackDataSuccess getOrderByTrackDataSuccess) {
        assertThat(getOrderByTrackDataSuccess.getOrder(), GetOrderByTrackDataSuccess.EXPECTED_NOT_NULL);
    }

}
