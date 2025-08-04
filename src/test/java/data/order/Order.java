package data.order;

import config.Config;
import data.order.create.CreateOrderDataRequest;
import data.order.create.scooter.Color;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<Color> color;
    private final CreateOrderDataRequest createOrderDataRequest;

    public Order(CreateOrderDataRequest createOrderDataRequest) {
        this.createOrderDataRequest = createOrderDataRequest;
        this.firstName = createOrderDataRequest.getFirstName();
        this.lastName = createOrderDataRequest.getLastName();
        this.address = createOrderDataRequest.getAddress();
        this.metroStation = createOrderDataRequest.getMetroStation();
        this.phone = createOrderDataRequest.getPhone();
        this.rentTime = createOrderDataRequest.getRentTime();
        this.deliveryDate = createOrderDataRequest.getDeliveryDate();
        this.comment = createOrderDataRequest.getComment();
        this.color = createOrderDataRequest.getColor();
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Config.getBaseURI())
            .setContentType(ContentType.JSON)
            .build();

    public Response createOrderRequest() {
        return given()
                .spec(requestSpecification)
                .body(createOrderDataRequest)
                .post(Config.getCreateOrderEndpoint());
    }

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

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

}
