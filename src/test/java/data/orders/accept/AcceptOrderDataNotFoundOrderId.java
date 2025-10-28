package data.orders.accept;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderDataNotFoundOrderId {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 404;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE + " Not Found";
    private static final String EXPECTED_MESSAGE = "Заказа с таким id не существует";
    private static final AcceptOrderDataNotFoundOrderId ACCEPT_ORDER_DATA_NOT_FOUND = new AcceptOrderDataNotFoundOrderId(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(ACCEPT_ORDER_DATA_NOT_FOUND)))
            .build();

    public AcceptOrderDataNotFoundOrderId(String message) {
        this.message = message;
    }

    public AcceptOrderDataNotFoundOrderId() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
