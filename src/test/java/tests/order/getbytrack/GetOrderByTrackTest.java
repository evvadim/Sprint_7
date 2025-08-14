package tests.order.getbytrack;

import data.orders.GetOrderByTrack;
import data.orders.GetOrdersPageByPage;
import data.orders.get.bytrack.GetOrderByTrackDataSuccess;
import data.orders.get.pagebypage.GetOrdersPageByPageDataSuccess;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class GetOrderByTrackTest {

    private Integer track;
    
    @Before
    public void setUp() {
        
        // запросим список заказов и возьмем случайный
        GetOrdersPageByPage getOrdersPageByPage = new GetOrdersPageByPage();
        Response responseDefault = getOrdersPageByPage.getOrdersRequest(1, 0);
        GetOrdersPageByPageDataSuccess getOrdersPageByPageDataSuccessDefault = responseDefault.body().as(GetOrdersPageByPageDataSuccess.class);
        Integer total = getOrdersPageByPageDataSuccessDefault.getPageInfo().getTotal();

        Response response = getOrdersPageByPage.getOrdersRequest(1, (int) (Math.random() * (total - 1)));
        GetOrdersPageByPageDataSuccess getOrdersPageByPageDataSuccess = response.body().as(GetOrdersPageByPageDataSuccess.class);
        
        // из случайного заказа извлекаем `track`
        track = getOrdersPageByPageDataSuccess.getOrders().get(0).getTrack();

    }

    @Test
    public void getOrderByTrackTest() {

        GetOrderByTrack getOrderByTrack = new GetOrderByTrack(track);
        Response response = getOrderByTrack.getOrderByTrackRequest();

        GetOrderByTrackDataSuccess getOrderByTrackDataSuccess = response.body().as(GetOrderByTrackDataSuccess.class);

    }
}
