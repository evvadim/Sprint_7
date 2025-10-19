package data.orders;

import config.Config;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CancelOrder {

    private Integer track;

    public CancelOrder(Integer track) {
        this.track = track;
    }

    public CancelOrder() {
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Cancel Order Request")
    public Response cancelOrderRequest() {
        return given()
                .spec(requestSpecification)
                .queryParam("track", track)
                .put(Config.getCancelOrderEndpoint());
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
