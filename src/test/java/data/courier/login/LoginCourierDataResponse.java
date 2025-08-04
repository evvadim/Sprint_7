package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierDataResponse {

    private String id;

    // ожидаем получить
    public static final Matcher<Object> expectedNotNull = notNullValue();
    // если не получаем, то текст ошибки
    public static final String unexpectedNotNullErrorMessage = "Неуспешный логин курьера с валидными и обязательными полями";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public LoginCourierDataResponse(String id) {
        this.id = id;
    }

    public LoginCourierDataResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
