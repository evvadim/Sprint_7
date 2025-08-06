package data.courier.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class LoginCourierDataBadRequest {

    private String message;

    // ожидаем получить
    public static final String expectedMessage = "Недостаточно данных для входа";
    // если не получаем, то текст ошибки
    public static final String unexpectedErrorMessage = "Сообщение об ошибке не соответствует документации";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
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
