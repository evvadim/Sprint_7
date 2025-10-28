package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.*;

public class LoginCourierDataSuccess {

    private Integer id;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final String EXPECTED_STATUS_LINE = "HTTP/1.1 " + EXPECTED_CODE;
    public static final Matcher<Object> EXPECTED_NOT_NULL = notNullValue();

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectStatusLine(EXPECTED_STATUS_LINE)
            .build();

    public LoginCourierDataSuccess(Integer id) {
        this.id = id;
    }

    public LoginCourierDataSuccess() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
