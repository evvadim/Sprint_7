package data.courier.create;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierDataCreated {

    private boolean ok;

    // ожидаем получить
    private static final Integer EXPECTED_CODE = 201;
    private static final boolean EXPECTED_OK = true;
    private static final CreateCourierDataCreated CREATE_COURIER_DATA_CREATED = new CreateCourierDataCreated(EXPECTED_OK);

    // спецификация ответа
    public static final ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(EXPECTED_CODE)
            .expectBody(equalTo(new Gson().toJson(CREATE_COURIER_DATA_CREATED)))
            .build();

    public CreateCourierDataCreated(boolean ok) {
        this.ok = ok;
    }

    public CreateCourierDataCreated() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}
