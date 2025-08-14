package data.orders.get.bytrack;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetOrderByTrackDataBadRequest {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 400;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE + " Bad Request";
    private static final String EXPECTED_MESSAGE = "Недостаточно данных для поиска";
    private static final GetOrderByTrackDataBadRequest GET_ORDER_BY_TRACK_DATA_BAD_REQUEST = new GetOrderByTrackDataBadRequest(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(GET_ORDER_BY_TRACK_DATA_BAD_REQUEST)))
            .build();

    public GetOrderByTrackDataBadRequest(String message) {
        this.message = message;
    }

    public GetOrderByTrackDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
