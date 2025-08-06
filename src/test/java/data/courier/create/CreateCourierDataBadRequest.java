package data.courier.create;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class CreateCourierDataBadRequest {

    private String message;

    // ожидаем получить
    public static final String expectedMessage = "Недостаточно данных для создания учетной записи";
    // если не получаем, то текст ошибки
    public static final String unexpectedErrorMessage = "Сообщение об ошибке не соответствует документации";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();

    public CreateCourierDataBadRequest(String message) {
        this.message = message;
    }

    public CreateCourierDataBadRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
