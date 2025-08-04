package data.courier.login;

public class LoginCourierDataRequest {

    private String login;
    private String password;

    public LoginCourierDataRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourierDataRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
