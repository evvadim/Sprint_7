package data.orders.get.pagebypage;

import data.orders.get.pagebypage.elements.Order;
import data.orders.get.pagebypage.elements.PageInfo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersPageByPageDataSuccess {

    private List<Order> orders;
    private PageInfo pageInfo;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .build();

    public GetOrdersPageByPageDataSuccess(List<Order> orders, PageInfo pageInfo) {
        this.orders = orders;
        this.pageInfo = pageInfo;
    }

    public GetOrdersPageByPageDataSuccess() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
