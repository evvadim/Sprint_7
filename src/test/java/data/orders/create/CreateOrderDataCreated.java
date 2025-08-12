package data.orders.create;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateOrderDataCreated {

    private Integer track;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 201;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();


    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
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
