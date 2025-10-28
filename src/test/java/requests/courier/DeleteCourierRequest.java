package requests.courier;

import config.endpoints.Endpoints;
import data.courier.CourierData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static requests.courier.CommonCourierRequest.requestSpecification;
import static requests.courier.CommonCourierRequest.checkResponseSpecs;

public class DeleteCourierRequest {

    private final Integer id;

    public DeleteCourierRequest(CourierData courierData) {
        this.id = courierData.getId();
    }

    @Step("Delete Courier DELETE Request")
    private Response deleteCourierRequestAndCheckResponseSpecs(ResponseSpecification specification) {

        String deletePath =
                (id == null) ? (Endpoints.DELETE_COURIER) : (String.format("%s/%d", Endpoints.DELETE_COURIER, id));

        Response response = given()
                .spec(requestSpecification)
                .delete(deletePath);

        if (specification != null) {
            checkResponseSpecs(response, specification);
        }

        return response;

    }

    public void deleteCourierRequest() {
        deleteCourierRequestAndCheckResponseSpecs(null);
    }

    public void deleteCourierRequest(ResponseSpecification specification) {
        deleteCourierRequestAndCheckResponseSpecs(specification);
    }

}
