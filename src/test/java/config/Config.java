package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String userLogin;
    private static final String userPassword;
    private static final String userFirstName;
    private static final String baseURI;
    private static final String createCourierEndpoint;
    private static final String loginCourierEndpoint;
    private static final String deleteCourierEndpoint;
    private static final String createOrderEndpoint;
    private static final String wrongUserLogin;
    private static final String wrongUserPassword;
    private static final String scooterColorBlack;
    private static final String scooterColorGrey;


    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/config/resource.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        userLogin = properties.getProperty("courierLogin");
        userPassword = properties.getProperty("courierPassword");
        userFirstName = properties.getProperty("courierFirstName");
        baseURI = properties.getProperty("baseURI");
        createCourierEndpoint = properties.getProperty("createCourierEndpoint");
        loginCourierEndpoint = properties.getProperty("loginCourierEndpoint");
        deleteCourierEndpoint = properties.getProperty("deleteCourierEndpoint");
        createOrderEndpoint = properties.getProperty("createOrderEndpoint");
        wrongUserLogin = properties.getProperty("wrongCourierLogin");
        wrongUserPassword = properties.getProperty("wrongCourierPassword");
        scooterColorGrey = properties.getProperty("scooterColorGrey");
        scooterColorBlack = properties.getProperty("scooterColorBlack");

    }

    public static String getUserLogin() {
        return userLogin;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static String getUserFirstName() {
        return userFirstName;
    }

    public static String getBaseURI() {
        return baseURI;
    }

    public static String getCreateCourierEndpoint() {
        return createCourierEndpoint;
    }

    public static String getLoginCourierEndpoint() {
        return loginCourierEndpoint;
    }

    public static String getDeleteCourierEndpoint() {
        return deleteCourierEndpoint;
    }

    public static String getCreateOrderEndpoint() {
        return createOrderEndpoint;
    }

    public static String getWrongUserLogin() {
        return wrongUserLogin;
    }

    public static String getWrongUserPassword() {
        return wrongUserPassword;
    }

    public static String getScooterColorBlack() {
        return scooterColorBlack;
    }

    public static String getScooterColorGrey() {
        return scooterColorGrey;
    }

}
