package data.courier.create;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierDataConflict {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 409;
    private static final String EXPECTED_MESSAGE = "Этот логин уже используется";
    private static final CreateCourierDataConflict CREATE_COURIER_DATA_CONFLICT = new CreateCourierDataConflict(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPECIFICATION = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(CREATE_COURIER_DATA_CONFLICT)))
            .build();

    public CreateCourierDataConflict(String message) {
        this.message = message;
    }

    public CreateCourierDataConflict() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
