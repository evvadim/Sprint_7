package data.courier.create;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierDataSuccess {

    private Boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 201;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE + " Created";
    private static final Boolean EXPECTED_OK = true;
    private static final CreateCourierDataSuccess CREATE_COURIER_DATA_CREATED = new CreateCourierDataSuccess(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .expectBody(equalTo(new Gson().toJson(CREATE_COURIER_DATA_CREATED)))
            .build();

    public CreateCourierDataSuccess(Boolean ok) {
        this.ok = ok;
    }

    public CreateCourierDataSuccess() {
    }

    public Boolean isOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
