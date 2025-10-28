package data.orders.get.pagebypage;

import data.orders.get.pagebypage.elements.AvailableStations;
import data.orders.get.pagebypage.elements.Orders;
import data.orders.get.pagebypage.elements.PageInfo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersPageByPageDataSuccess {

    private List<Orders> orders;
    private PageInfo pageInfo;
    private List<AvailableStations> availableStations;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .build();

    public GetOrdersPageByPageDataSuccess(List<Orders> orders, PageInfo pageInfo, List<AvailableStations> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public GetOrdersPageByPageDataSuccess() {
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }
}
