package data.orders.accept;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderDataAccepted {

    private Boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    private static final Boolean EXPECTED_OK = true;
    private static final AcceptOrderDataAccepted ACCEPT_ORDER_DATA_ACCEPTED = new AcceptOrderDataAccepted(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(ACCEPT_ORDER_DATA_ACCEPTED)))
            .build();

    public AcceptOrderDataAccepted(Boolean ok) {
        this.ok = ok;
    }

    public AcceptOrderDataAccepted() {
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
