package requests.orders;

import config.Config;
import config.endpoints.Endpoints;
import data.orders.AcceptOrderData;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class AcceptOrderRequest {

    private final Integer id;
    private final Integer courierId;

    public AcceptOrderRequest(AcceptOrderData acceptOrderData) {
        this.id = acceptOrderData.getId();
        this.courierId = acceptOrderData.getCourierId();
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Accept Order Request")
    private Response acceptOrderAndCheckResponseSpecs(ResponseSpecification specification) {

        String putPath =
                (id == null) ? (Endpoints.ACCEPT_ORDER) : (String.format("%s/%d", Endpoints.ACCEPT_ORDER, id));

        Response response = given()
                .spec(requestSpecification)
                .queryParam("courierId", courierId)
                .put(putPath);

        if (specification != null) {
            checkResponseSpecs(response, specification);
        }

        return response;

    }

    public Response acceptOrderRequest() {
        return acceptOrderAndCheckResponseSpecs(null);
    }

    public Response acceptOrderRequest(ResponseSpecification specification) {
        return acceptOrderAndCheckResponseSpecs(specification);
    }

    @Step("Check Response Specification")
    public void checkResponseSpecs(Response response, ResponseSpecification responseSpecification) {
        response.then().spec(responseSpecification);
    }

}
