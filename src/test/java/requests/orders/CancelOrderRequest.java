package requests.orders;

import config.Config;
import config.endpoints.Endpoints;
import data.orders.CancelOrderData;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CancelOrderRequest {

    private final Integer track;

    public CancelOrderRequest(CancelOrderData cancelOrderData) {
        this.track = cancelOrderData.getTrack();
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
                .put(Endpoints.CANCEL_ORDER);
    }

}
