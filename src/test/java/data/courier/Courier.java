package data.courier;

import config.Config;
import config.endpoints.Endpoints;
import data.courier.create.CreateCourierDataRequest;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataSuccess;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class Courier {

    private String login;
    private String password;
    private String firstName;
    private Integer id;
    private final CreateCourierDataRequest createCourierDataRequest;
    private final LoginCourierDataRequest loginCourierDataRequest;

    public Courier(CreateCourierDataRequest createCourierDataRequest) {
        this.createCourierDataRequest = createCourierDataRequest;
        this.loginCourierDataRequest = new LoginCourierDataRequest(createCourierDataRequest.getLogin(), createCourierDataRequest.getPassword());
        this.login = createCourierDataRequest.getLogin();
        this.password = createCourierDataRequest.getPassword();
        this.firstName = createCourierDataRequest.getFirstName();
    }

    public Courier(LoginCourierDataRequest loginCourierDataRequest) {
        this.loginCourierDataRequest = loginCourierDataRequest;
        this.createCourierDataRequest = new CreateCourierDataRequest(loginCourierDataRequest.getLogin(), loginCourierDataRequest.getPassword(), null);
        this.login = loginCourierDataRequest.getLogin();
        this.password = loginCourierDataRequest.getPassword();
        this.firstName = null;
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Create Courier POST Request")
    private void createCourierRequestAndCheckResponseSpecs(ResponseSpecification specification) {

        Response response = given()
                .spec(requestSpecification)
                .body(createCourierDataRequest)
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
                .spec(requestSpecification)
                .body(loginCourierDataRequest)
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

    @Step("Check Response Specification")
    public void checkResponseSpecs(Response response, ResponseSpecification responseSpecification) {
        response.then().spec(responseSpecification);
    }

    @Step("Cast response to Class<T> and return Object")
    public <T> Object extractResponseToObject(Response response, Class<T> anyClass) {
        return response.body().as(anyClass);
    }

    @Step("Not Null Response when Courier Login Check")
    public void successLoginCourierCheck(LoginCourierDataSuccess loginCourierDataSuccess) {
        assertThat(loginCourierDataSuccess.getId(), LoginCourierDataSuccess.EXPECTED_NOT_NULL);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.createCourierDataRequest.setLogin(login);
        this.loginCourierDataRequest.setLogin(login);
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.createCourierDataRequest.setPassword(password);
        this.loginCourierDataRequest.setPassword(password);
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.createCourierDataRequest.setFirstName(firstName);
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
