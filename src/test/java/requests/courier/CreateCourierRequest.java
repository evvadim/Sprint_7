package requests.courier;

import config.endpoints.Endpoints;
import data.courier.CreateCourierData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static requests.courier.CommonCourierRequest.checkResponseSpecs;

public class CreateCourierRequest {

    private final CreateCourierData createCourierData;

    public CreateCourierRequest(CreateCourierData createCourierData) {
        this.createCourierData = createCourierData;
    }

    @Step("Create Courier POST Request")
    private void createCourierRequestAndCheckResponseSpecs(ResponseSpecification specification) {

        Response response = given()
                .spec(CommonCourierRequest.requestSpecification)
                .body(createCourierData)
                .post(Endpoints.CREATE_COURIER);

        if (specification != null) {
            checkResponseSpecs(response, specification);
        }

    }

    public void createCourierRequest() {
        createCourierRequestAndCheckResponseSpecs(null);
    }

    public void createCourierRequest(ResponseSpecification specification) {
        createCourierRequestAndCheckResponseSpecs(specification);
    }

}
