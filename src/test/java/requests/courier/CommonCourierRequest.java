package requests.courier;

import config.Config;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CommonCourierRequest {

     public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Check Response Specification")
    public static void checkResponseSpecs(Response response, ResponseSpecification responseSpecification) {
        response.then().spec(responseSpecification);
    }



}
