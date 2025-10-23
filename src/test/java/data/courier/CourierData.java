package data.courier;

public class CourierData {

    private String login;
    private String password;
    private String firstName;
    private Integer id;
    private final CreateCourierData createCourierData;
    private final LoginCourierData loginCourierData;

    public CourierData(CreateCourierData createCourierData) {
        this.createCourierData = createCourierData;
        this.loginCourierData = new LoginCourierData(createCourierData.getLogin(), createCourierData.getPassword());
        this.login = createCourierData.getLogin();
        this.password = createCourierData.getPassword();
        this.firstName = createCourierData.getFirstName();
    }

    public CourierData(LoginCourierData loginCourierData) {
        this.loginCourierData = loginCourierData;
        this.createCourierData = new CreateCourierData(loginCourierData.getLogin(), loginCourierData.getPassword(), null);
        this.login = loginCourierData.getLogin();
        this.password = loginCourierData.getPassword();
        this.firstName = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.createCourierData.setLogin(login);
        this.loginCourierData.setLogin(login);
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.createCourierData.setPassword(password);
        this.loginCourierData.setPassword(password);
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.createCourierData.setFirstName(firstName);
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
