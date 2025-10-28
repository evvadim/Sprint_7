package data.orders.accept;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderDataSuccess {

    private Boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    private static final Boolean EXPECTED_OK = true;
    private static final AcceptOrderDataSuccess ACCEPT_ORDER_DATA_ACCEPTED = new AcceptOrderDataSuccess(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(ACCEPT_ORDER_DATA_ACCEPTED)))
            .build();

    public AcceptOrderDataSuccess(Boolean ok) {
        this.ok = ok;
    }

    public AcceptOrderDataSuccess() {
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
