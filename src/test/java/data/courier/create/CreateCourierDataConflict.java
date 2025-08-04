package data.courier.create;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class CreateCourierDataConflict {

    private Integer code;
    private String message;

    // ожидаем получить
    public static final String expectedMessage = "Этот логин уже используется";
    // если не получаем, то текст ошибки
    public static final String unexpectedErrorMessage = "Сообщение об ошибке не соответствует документации";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(409)
            .build();

    public CreateCourierDataConflict(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CreateCourierDataConflict() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
