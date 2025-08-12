package data.courier.login;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierDataBadRequest {

    private String message;

    // ожидаем получить
    private static final int EXPECTED_CODE = 400;
    private static final String EXPECTED_MESSAGE = "Недостаточно данных для входа";
    private static final LoginCourierDataBadRequest LOGIN_COURIER_DATA_BAD_REQUEST = new LoginCourierDataBadRequest(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(LOGIN_COURIER_DATA_BAD_REQUEST)))
            .build();

    public LoginCourierDataBadRequest(String message) {
        this.message = message;
    }

    public LoginCourierDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
