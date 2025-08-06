package data.courier.delete;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class DeleteCourierDataNotFound {

    private String message;

    // ожидаем получить
    public static final String expectedMessage = "Курьера с таким id нет";
    // если не получаем, то текст ошибки
    public static final String unexpectedErrorMessage = "Сообщение об ошибке не соответствует документации";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();

    public DeleteCourierDataNotFound(String message) {
        this.message = message;
    }

    public DeleteCourierDataNotFound() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
