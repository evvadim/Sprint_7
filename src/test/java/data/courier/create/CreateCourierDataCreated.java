package data.courier.create;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class CreateCourierDataCreated {

    private boolean ok;

    // ожидаем получить
    public static final boolean expectedOk = true;
    // если не получаем, то текст ошибки
    public static final String unexpectedOkErrorMessage = "Курьер с валидными и обязательными полями не был создан";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    public CreateCourierDataCreated(boolean ok) {
        this.ok = ok;
    }

    public CreateCourierDataCreated() {
    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}
