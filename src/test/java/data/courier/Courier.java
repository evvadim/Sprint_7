package data.courier;

import config.Config;
import data.courier.create.CreateCourierDataCreated;
import data.courier.create.CreateCourierDataRequest;
import data.courier.login.LoginCourierDataRequest;
import data.courier.login.LoginCourierDataLoggedIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

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

    public Response createCourierRequest() {
        Response response = given()
                .spec(requestSpecification)
                .body(createCourierDataRequest)
                .post(Config.getCreateCourierEndpoint());
        this.id = loginCourierRequest().then().extract().as(LoginCourierDataLoggedIn.class).getId();
        return response;
    }

    public Response loginCourierRequest() {
        Response response = given()
                .spec(requestSpecification)
                .body(loginCourierDataRequest)
                .post(Config.getLoginCourierEndpoint());
        this.id = response.then().extract().as(LoginCourierDataLoggedIn.class).getId();
        return response;
    }

    public Response deleteCourierRequest() {
        String deletePath =
                (id == null) ? (Config.getDeleteCourierEndpoint()) : (String.format("%s/%d", Config.getDeleteCourierEndpoint(), id));
        return given()
                .spec(requestSpecification)
                .delete(deletePath);
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
