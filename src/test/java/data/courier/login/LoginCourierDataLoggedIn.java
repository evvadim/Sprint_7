package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.*;

public class LoginCourierDataLoggedIn {

    private Integer id;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
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
