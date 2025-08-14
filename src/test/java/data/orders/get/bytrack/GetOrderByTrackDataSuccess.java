package data.orders.get.bytrack;

import data.orders.get.bytrack.elements.Order;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderByTrackDataSuccess {

    private Order order;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .build();

    public GetOrderByTrackDataSuccess(Order order) {
        this.order = order;
    }

    public GetOrderByTrackDataSuccess() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
