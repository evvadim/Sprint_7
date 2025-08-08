package data.orders;

import config.Config;
import data.orders.create.CreateOrderDataRequest;
import data.scooter.ScooterColor;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrder {

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<ScooterColor> scooterColor;
    private final CreateOrderDataRequest createOrderDataRequest;

    public CreateOrder(CreateOrderDataRequest createOrderDataRequest) {
        this.createOrderDataRequest = createOrderDataRequest;
        this.firstName = createOrderDataRequest.getFirstName();
        this.lastName = createOrderDataRequest.getLastName();
        this.address = createOrderDataRequest.getAddress();
        this.metroStation = createOrderDataRequest.getMetroStation();
        this.phone = createOrderDataRequest.getPhone();
        this.rentTime = createOrderDataRequest.getRentTime();
        this.deliveryDate = createOrderDataRequest.getDeliveryDate();
        this.comment = createOrderDataRequest.getComment();
        this.scooterColor = createOrderDataRequest.getScooterColorList();
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Create Order Request")
    public Response createOrderRequest() {
        return given()
                .spec(requestSpecification)
                .body(createOrderDataRequest)
                .post(Config.getCreateOrderEndpoint());
    }

    @Step("Cancel Order with `track` Request")
    public void cancelOrderRequest(Integer track) {
        given()
                .spec(requestSpecification)
                .queryParam("track", track)
                .put(Config.getCancelOrderEndpoint());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(Integer metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ScooterColor> getScooterColor() {
        return scooterColor;
    }

    public void setScooterColor(List<ScooterColor> scooterColor) {
        this.scooterColor = scooterColor;
    }

}
