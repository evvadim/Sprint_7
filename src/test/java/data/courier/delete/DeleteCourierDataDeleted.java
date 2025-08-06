package data.courier.delete;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class DeleteCourierDataDeleted {

    private boolean ok;

    // ожидаем получить
    public static final boolean expectedOk = true;
    // если не получаем, то текст ошибки
    public static final String unexpectedOkErrorMessage = "Курьер с валидным id не был удален";

    // спецификация ответа
    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public DeleteCourierDataDeleted(boolean ok) {
        this.ok = ok;
    }

    public DeleteCourierDataDeleted() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}
