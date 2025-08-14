package data.courier.delete;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierDataSuccess {

    private Boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE + " OK";
    private static final Boolean EXPECTED_OK = true;
    private static final DeleteCourierDataSuccess DELETE_COURIER_DATA_DELETED = new DeleteCourierDataSuccess(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(DELETE_COURIER_DATA_DELETED)))
            .build();

    public DeleteCourierDataSuccess(Boolean ok) {
        this.ok = ok;
    }

    public DeleteCourierDataSuccess() {
    }

    public Boolean isOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
