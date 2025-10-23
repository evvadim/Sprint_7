package tests.courier.delete;

import config.Config;
import data.courier.CourierData;
import data.courier.CreateCourierData;
import data.courier.delete.DeleteCourierDataBadRequest;
import data.courier.delete.DeleteCourierDataSuccess;
import data.courier.delete.DeleteCourierDataNotFound;
import data.courier.LoginCourierData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.courier.DeleteCourierRequest;
import requests.courier.LoginCourierRequest;

public class DeleteCourierTest {
    
    CreateCourierData createCourierData = new CreateCourierData(Config.getUserLogin(), Config.getUserPassword(), Config.getUserFirstName());
    CourierData courierData;
    Integer idBackup;

    @Before
    public void setUp() {

        courierData = new CourierData(createCourierData);
        courierData.createCourierRequest();

    }

    @Test
    public void deleteCourierValidDataExpectedOk() {
        new LoginCourierRequest(courierData).loginCourierRequest();
        new DeleteCourierRequest(courierData).deleteCourierRequest(DeleteCourierDataSuccess.RESPONSE_SPEC);
    }

    @Test
    public void deleteCourierRequestWithoutId() {

        // авторизуемся чтобы получить значение `id`
        new LoginCourierRequest(courierData).loginCourierRequest();

        // бекапим значение `id` для удаления этого курьера в методе @After
        idBackup = courierData.getId();

        // устанавливаем значение `id` равное `null`
        courierData.setId(null);

        // пытаемся удалить созданного курьера
        new DeleteCourierRequest(courierData).deleteCourierRequest(DeleteCourierDataBadRequest.RESPONSE_SPEC);
    }

    @Test
    public void deleteCourierRequestIdNotFound() {

        // создадим объект курьера-клона с такими же учетными данными
        LoginCourierData loginCourierData = new LoginCourierData(Config.getUserLogin(), Config.getUserPassword());
        CourierData courierDataClone = new CourierData(loginCourierData);

        // авторизуемся чтобы получить значение свойства `id`
        new LoginCourierRequest(courierDataClone).loginCourierRequest();

        // удаляем основного курьера
        // перед этим авторизуемся чтобы получить `id`
        new LoginCourierRequest(courierData).loginCourierRequest();
        new DeleteCourierRequest(courierData).deleteCourierRequest();
        // таким образом в объекте `courierClone` остались данные учетной записи и значение поля `id` курьера,
        // который был удален из таблицы `Couriers`

        // пытаемся удалить курьера с `id`, которого уже не существует
        new DeleteCourierRequest(courierDataClone).deleteCourierRequest(DeleteCourierDataNotFound.RESPONSE_SPEC);

    }

    @After
    public void tearDown() {
        if (idBackup != null) {
            courierData.setId(idBackup);
            new DeleteCourierRequest(courierData).deleteCourierRequest();
        }
    }
}
