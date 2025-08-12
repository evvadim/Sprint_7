package data.courier.login;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierDataNotFound {

    private String message;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 404;
    private static final String EXPECTED_MESSAGE = "Учетная запись не найдена";
    private static final LoginCourierDataNotFound LOGIN_COURIER_DATA_NOT_FOUND = new LoginCourierDataNotFound(EXPECTED_MESSAGE);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(LOGIN_COURIER_DATA_NOT_FOUND)))
            .build();

    public LoginCourierDataNotFound(String message) {
        this.message = message;
    }

    public LoginCourierDataNotFound() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
