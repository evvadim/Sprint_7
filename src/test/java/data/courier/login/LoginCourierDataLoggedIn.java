package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierDataLoggedIn {

    private Integer id;

    // ожидаем получить
    public static final Matcher<Object> expectedNotNull = notNullValue();
    // если не получаем, то текст ошибки
    public static final String unexpectedNotNullErrorMessage = "Неуспешный логин курьера с валидными и обязательными полями";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public LoginCourierDataLoggedIn(Integer id) {
        this.id = id;
    }

    public LoginCourierDataLoggedIn() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
