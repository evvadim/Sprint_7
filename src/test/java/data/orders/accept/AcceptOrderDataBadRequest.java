package data.orders.accept;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderDataBadRequest {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 400;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE + " Bad Request";
    private static final String EXPECTED_MESSAGE = "Недостаточно данных для поиска";
    private static final AcceptOrderDataBadRequest ACCEPT_ORDER_DATA_BAD_REQUEST = new AcceptOrderDataBadRequest(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(ACCEPT_ORDER_DATA_BAD_REQUEST)))
            .build();

    public AcceptOrderDataBadRequest(String message) {
        this.message = message;
    }

    public AcceptOrderDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
