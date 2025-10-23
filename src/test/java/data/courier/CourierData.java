package data.courier;

import config.endpoints.Endpoints;
import data.courier.login.LoginCourierDataSuccess;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import requests.courier.CommonCourierRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static requests.courier.CommonCourierRequest.checkResponseSpecs;

public class CourierData {

    private String login;
    private String password;
    private String firstName;
    private Integer id;
    private final CreateCourierData createCourierData;
    private final LoginCourierData loginCourierData;

    public CourierData(CreateCourierData createCourierData) {
        this.createCourierData = createCourierData;
        this.loginCourierData = new LoginCourierData(createCourierData.getLogin(), createCourierData.getPassword());
        this.login = createCourierData.getLogin();
        this.password = createCourierData.getPassword();
        this.firstName = createCourierData.getFirstName();
    }

    public CourierData(LoginCourierData loginCourierData) {
        this.loginCourierData = loginCourierData;
        this.createCourierData = new CreateCourierData(loginCourierData.getLogin(), loginCourierData.getPassword(), null);
        this.login = loginCourierData.getLogin();
        this.password = loginCourierData.getPassword();
        this.firstName = null;
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

//    @Step("Delete Courier DELETE Request")
//    private Response deleteCourierRequestAndCheckResponseSpecs(ResponseSpecification specification) {
//
//        String deletePath =
//                (id == null) ? (Endpoints.DELETE_COURIER) : (String.format("%s/%d", Endpoints.DELETE_COURIER, id));
//
//        Response response = given()
//                .spec(CommonCourierRequest.requestSpecification)
//                .delete(deletePath);
//
//        if (specification != null) {
//            checkResponseSpecs(response, specification);
//        }
//
//        return response;
//
//    }
//
//    public void deleteCourierRequest() {
//        deleteCourierRequestAndCheckResponseSpecs(null);
//    }
//
//    public void deleteCourierRequest(ResponseSpecification specification) {
//        deleteCourierRequestAndCheckResponseSpecs(specification);
//    }
//
//    @Step("Check Response Specification")
//    public void checkResponseSpecs(Response response, ResponseSpecification responseSpecification) {
//        response.then().spec(responseSpecification);
//    }

    @Step("Cast response to Class<T> and return Object")
    public <T> Object extractResponseToObject(Response response, Class<T> anyClass) {
        return response.body().as(anyClass);
    }

//    @Step("Not Null Response when Courier Login Check")
//    public void successLoginCourierCheck(LoginCourierDataSuccess loginCourierDataSuccess) {
//        assertThat(loginCourierDataSuccess.getId(), LoginCourierDataSuccess.EXPECTED_NOT_NULL);
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.createCourierData.setLogin(login);
        this.loginCourierData.setLogin(login);
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.createCourierData.setPassword(password);
        this.loginCourierData.setPassword(password);
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.createCourierData.setFirstName(firstName);
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
