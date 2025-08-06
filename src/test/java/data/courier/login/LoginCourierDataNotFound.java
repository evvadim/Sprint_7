package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class LoginCourierDataNotFound {

    private String message;

    // ожидаем получить
    public static final String expectedMessage = "Учетная запись не найдена";
    // если не получаем, то текст ошибки
    public static final String unexpectedErrorMessage = "Сообщение об ошибке не соответствует документации";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
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
