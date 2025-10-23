package requests.courier;

import config.endpoints.Endpoints;
import data.courier.CourierData;
import data.courier.LoginCourierData;
import data.courier.login.LoginCourierDataSuccess;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static requests.courier.CommonCourierRequest.checkResponseSpecs;

public class LoginCourierRequest {

    private final String login;
    private final String password;
    private Integer id;
    private final LoginCourierData loginCourierData;

    public LoginCourierRequest(CourierData courierData) {
        this.login = courierData.getLogin();
        this.password = courierData.getPassword();
        this.id = courierData.getId();
        this.loginCourierData = new LoginCourierData(this.login, this.password);
    }

    @Step("Login Courier POST Request")
    private Response loginCourierRequestAndCheckResponseSpecs(ResponseSpecification specification) {

        Response response = given()
                .spec(CommonCourierRequest.requestSpecification)
                .body(loginCourierData)
                .post(Endpoints.LOGIN_COURIER);
        this.id = response.then().extract().as(LoginCourierDataSuccess.class).getId();

        if (specification != null) {
            checkResponseSpecs(response, specification);
        }

        return response;
    }

    public void loginCourierRequest() {
        loginCourierRequestAndCheckResponseSpecs(null);
    }

    public Response loginCourierRequest(ResponseSpecification specification) {
        return loginCourierRequestAndCheckResponseSpecs(specification);
    }

    @Step("Not Null Response when Courier Login Check")
    public void successLoginCourierCheck(LoginCourierDataSuccess loginCourierDataSuccess) {
        assertThat(loginCourierDataSuccess.getId(), LoginCourierDataSuccess.EXPECTED_NOT_NULL);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }
}
