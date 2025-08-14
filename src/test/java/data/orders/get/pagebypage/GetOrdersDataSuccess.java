package data.orders.get.pagebypage;

import data.orders.get.pagebypage.elements.Order;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersDataSuccess {

    private List<Order> orders;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .build();

    public GetOrdersDataSuccess(List<Order> orders) {
        this.orders = orders;
    }

    public GetOrdersDataSuccess() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
