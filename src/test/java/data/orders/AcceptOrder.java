package data.orders;

import config.Config;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class AcceptOrder {

    private Integer id;
    private Integer courierId;

    public AcceptOrder(Integer id, Integer courierId) {
        this.id = id;
        this.courierId = courierId;
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Accept Order Request")
    private Response acceptOrderAndCheckResponseSpecs(ResponseSpecification specification) {

        String putPath =
                (id == null) ? (Config.getAcceptOrderEndpoint()) : (String.format("%s/%d", Config.getAcceptOrderEndpoint(), id));

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }
}
