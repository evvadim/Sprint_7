package data.courier.create;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierDataBadRequest {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 400;
    private static final String EXPECTED_MESSAGE = "Недостаточно данных для создания учетной записи";
    private static final CreateCourierDataBadRequest CREATE_COURIER_DATA_BAD_REQUEST = new CreateCourierDataBadRequest(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(CREATE_COURIER_DATA_BAD_REQUEST)))
            .build();

    public CreateCourierDataBadRequest(String message) {
        this.message = message;
    }

    public CreateCourierDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
