package tests.courier.delete;

import config.Config;
import data.courier.Courier;
import data.courier.create.CreateCourierDataRequest;
import data.courier.delete.DeleteCourierDataBadRequest;
import data.courier.delete.DeleteCourierDataDeleted;
import data.courier.delete.DeleteCourierDataNotFound;
import data.courier.login.LoginCourierDataRequest;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteCourierTest {
    
    CreateCourierDataRequest createCourierDataRequest = new CreateCourierDataRequest(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    Courier courier;
    Integer idBackup;

    @Before
    public void setUp() {

        courier = new Courier(createCourierDataRequest);
        courier.createCourierRequest();

    }

    @Test
    public void deleteCourierValidDataExpectedOk() {

        Response response = courier.deleteCourierRequest(DeleteCourierDataDeleted.responseSpec);
        DeleteCourierDataDeleted deleteCourierDataDeleted = response.body().as(DeleteCourierDataDeleted.class);
        assertEquals(DeleteCourierDataDeleted.unexpectedOkErrorMessage, DeleteCourierDataDeleted.expectedOk, deleteCourierDataDeleted.isOk());
        
    }

    @Test
    public void deleteCourierRequestWithoutId() {

        // авторизуемся чтобы получить значение `id`
        courier.loginCourierRequest();

        // бекапим значение `id` для удаления этого курьера в методе @After
        idBackup = courier.getId();

        // устанавливаем значение `id` равное `null`
        courier.setId(null);

        // пытаемся удалить созданного курьера
        Response response = courier.deleteCourierRequest(DeleteCourierDataBadRequest.responseSpec);

        DeleteCourierDataBadRequest deleteCourierDataBadRequest = response.body().as(DeleteCourierDataBadRequest.class);
        assertEquals(DeleteCourierDataBadRequest.unexpectedErrorMessage, DeleteCourierDataBadRequest.expectedMessage, deleteCourierDataBadRequest.getMessage());
    }

    @Test
    public void deleteCourierRequestIdNotFound() {

        // создадим объект курьера-клона с такими же учетными данными
        LoginCourierDataRequest loginCourierDataRequest = new LoginCourierDataRequest(Config.getUserLogin(), Config.getUserPassword());
        Courier courierClone = new Courier(loginCourierDataRequest);

        // авторизуемся чтобы получить значение свойства `id`
        courierClone.loginCourierRequest();

        // удаляем основного курьера
        courier.deleteCourierRequest();
        // таким образом в объекте `courierClone` остались данные учетной записи и значение поля `id` курьера,
        // который был удален из таблицы `Couriers`

        // пытаемся удалить курьера с `id`, которого уже не существует
        Response response = courierClone.deleteCourierRequest(DeleteCourierDataNotFound.responseSpec);

        DeleteCourierDataNotFound deleteCourierDataNotFound = response.body().as(DeleteCourierDataNotFound.class);
        assertEquals(DeleteCourierDataNotFound.unexpectedErrorMessage, DeleteCourierDataNotFound.expectedMessage, deleteCourierDataNotFound.getMessage());

    }

    @After
    public void tearDown() {
        if (idBackup != null) {
            courier.setId(idBackup);
            courier.deleteCourierRequest();
        }
    }
}
