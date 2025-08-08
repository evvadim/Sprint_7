package data.courier.delete;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierDataDeleted {

    private boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 200;
    private static final boolean EXPECTED_OK = true;
    private static final DeleteCourierDataDeleted DELETE_COURIER_DATA_DELETED = new DeleteCourierDataDeleted(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(DELETE_COURIER_DATA_DELETED)))
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
