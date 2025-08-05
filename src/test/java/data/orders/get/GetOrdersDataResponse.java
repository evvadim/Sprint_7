package data.orders.get;

import data.orders.get.substructs.AvailableStations;
import data.orders.get.substructs.Order;
import data.orders.get.substructs.PageInfo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersDataResponse {

    private List<Order> orders;
    private PageInfo pageInfo;
    private List<AvailableStations> availableStations;

    // ожидаем получить
    public static final Matcher<Object> expectedNotNull = notNullValue();
    // если не получаем, то текст ошибки
    public static final String unexpectedOkErrorMessage = "Список заказов не был получен";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public GetOrdersDataResponse(List<Order> orders, PageInfo pageInfo, List<AvailableStations> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public GetOrdersDataResponse() {
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

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }

}
