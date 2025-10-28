package requests.orders;

import config.Config;
import config.endpoints.Endpoints;
import data.orders.CreateOrderData;
import data.orders.create.CreateOrderDataSuccess;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateOrderRequest {

    private final CreateOrderData createOrderData;

    public CreateOrderRequest(CreateOrderData createOrderData) {
        this.createOrderData = createOrderData;
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Create Order Request")
    public Response createOrderRequest() {
        return given()
                .spec(requestSpecification)
                .body(createOrderData)
                .post(Endpoints.CREATE_ORDER);
    }

    @Step("Not Null Response when Order Created Check")
    public void successOrderCreatedCheck(CreateOrderDataSuccess createOrderDataSuccess) {
        assertThat(createOrderDataSuccess.getTrack(), CreateOrderDataSuccess.EXPECTED_NOT_NULL);
    }

}
