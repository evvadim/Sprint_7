package data.courier.delete;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierDataBadRequest {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 400;
    private static final String EXPECTED_MESSAGE = "Недостаточно данных для удаления курьера";
    private static final DeleteCourierDataBadRequest DELETE_COURIER_DATA_BAD_REQUEST = new DeleteCourierDataBadRequest(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(DELETE_COURIER_DATA_BAD_REQUEST)))
            .build();

    public DeleteCourierDataBadRequest(String message) {
        this.message = message;
    }

    public DeleteCourierDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
