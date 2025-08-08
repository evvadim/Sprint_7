package tests.order.get;

import config.Config;
import data.orders.GetOrders;
import data.orders.get.GetOrdersDataResponse;
import data.orders.get.substructs.NearestStation;
import data.orders.get.substructs.Order;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GetOrdersTest {

    GetOrders getOrders;

    @Before
    public void setUp() {
        getOrders = new GetOrders();
    }

    @Test
    @DisplayName("Default Get Orders Request")
    public void getOrdersTest() {

        Response response = getOrders.getOrderRequest();
        response.then().spec(GetOrdersDataResponse.responseSpec);

        GetOrdersDataResponse getOrdersDataResponse = response.body().as(GetOrdersDataResponse.class);
        assertThat(GetOrdersDataResponse.unexpectedOkErrorMessage, getOrdersDataResponse.getOrders(), GetOrdersDataResponse.expectedNotNull);

    }

    @Test
    @DisplayName("Count of Orders on last page testing")
    public void getOrdersAtLastPageTest() {

        Response response = getOrders.getOrderRequest(Config.getOrdersLimit(), 0);
        GetOrdersDataResponse getOrdersDataResponse = response.body().as(GetOrdersDataResponse.class);

        // запросим общее количество заказов
        Integer total = getOrdersDataResponse.getPageInfo().getTotal();

        // запросим значание лимита заказов на одной странице
        Integer limit = getOrdersDataResponse.getPageInfo().getLimit();

        // количество страниц полностью заполненных заказами страниц
        Integer page = total / limit;

        // количество заказов на последней странице будет равно
        // остатку от деления общего количества заказов на лимит

        Response responseLastPage = getOrders.getOrderRequest(limit, page);
        GetOrdersDataResponse getOrdersDataResponseLastPage = responseLastPage.body().as(GetOrdersDataResponse.class);

        assertThat(GetOrdersDataResponse.unexpectedOkErrorMessage, getOrdersDataResponseLastPage.getOrders(), GetOrdersDataResponse.expectedNotNull);
        assertEquals("Количество заказов на последней страницы не равно ожидаемому", total % limit, getOrdersDataResponseLastPage.getOrders().size());

    }

    @Test
    @DisplayName("Get Orders. Nearest station filter")
    public void getOrdersNearestStation() {

        // определяем общее количество заказов
        Response response = getOrders.getOrderRequest(1, 0);
        GetOrdersDataResponse getOrdersDataResponse = response.body().as(GetOrdersDataResponse.class);
        Integer total = getOrdersDataResponse.getPageInfo().getTotal();

        // выбираем случаный
        Response responseRandomOrder = getOrders.getOrderRequest(1, ((int) (Math.random() * (total - 1))));
        GetOrdersDataResponse getOrdersDataResponseRandomOrder = responseRandomOrder.body().as(GetOrdersDataResponse.class);

        // создаем список с номером станции из случайного заказа
        List<String> randomStation = List.of(getOrdersDataResponseRandomOrder.getOrders().get(0).getMetroStation());
        NearestStation nearestStation = new NearestStation(randomStation);

        // оправляем запрос на поиск заказов с этим номером станции
        Response responseNearestStation = getOrders.getOrderRequest(nearestStation);
        GetOrdersDataResponse getOrdersDataResponseNearestStation = responseNearestStation.body().as(GetOrdersDataResponse.class);

        List<Order> orders = getOrdersDataResponseNearestStation.getOrders();

        Set<String> filteredOrderStations = new HashSet<>();

        for (Order order : orders) {
            filteredOrderStations.add(order.getMetroStation());
        }

        assertEquals("Финальная выдача не фильтруется по ожидаемой станции", randomStation, new ArrayList<>(filteredOrderStations));

    }

}
