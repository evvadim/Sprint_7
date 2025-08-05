package data.orders;

import com.google.gson.Gson;
import config.Config;
import data.orders.get.substructs.NearestStation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetOrders {

    public GetOrders() {
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .build();

    public Response getOrderRequest(Integer courierId, Integer limit, Integer page) {
        return given()
                .spec(requestSpecification)
                .queryParam("courierId", courierId)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .get(Config.getGetOrdersEndpoint());
    }

    public Response getOrderRequest(NearestStation nearestStation) {
        String nearestStationAsJson = new Gson().toJson(nearestStation.getNearestStation());
        return given()
                .spec(requestSpecification)
                .queryParam("nearestStation", nearestStationAsJson)
                .get(Config.getGetOrdersEndpoint());
    }

    public Response getOrderRequest(Integer courierId) {
        return given()
                .spec(requestSpecification)
                .queryParam("courierId", courierId)
                .get(Config.getGetOrdersEndpoint());
    }

    public Response getOrderRequest(Integer limit, Integer page) {
        return given()
                .spec(requestSpecification)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .get(Config.getGetOrdersEndpoint());
    }

    public Response getOrderRequest() {
        return given()
                .spec(requestSpecification)
                .get(Config.getGetOrdersEndpoint());
    }

}
