package data.orders.create;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateOrderDataCreated {

    private Integer track;

    // ожидаем получить
    public static final Matcher<Object> expectedNotNull = notNullValue();
    // если не получаем, то текст ошибки
    public static final String unexpectedOkErrorMessage = "Заказ с валидными и обязательными полями не был создан";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectBody(containsString("track"))
            .build();

    public CreateOrderDataCreated(Integer track) {
        this.track = track;
    }

    public CreateOrderDataCreated() {
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
