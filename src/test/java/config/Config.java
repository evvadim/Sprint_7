package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String baseURI;

    private static final String createCourierEndpoint;
    private static final String loginCourierEndpoint;
    private static final String deleteCourierEndpoint;

    private static final String createOrderEndpoint;
    private static final String cancelOrderEndpoint;
    private static final String getOrdersEndpoint;

    private static final String userLogin;
    private static final String userPassword;
    private static final String userFirstName;

    private static final String wrongUserLogin;
    private static final String wrongUserPassword;

    private static final String orderFirstName;
    private static final String orderLastName;
    private static final String orderAddress;
    private static final Integer orderMetroStation;
    private static final String orderPhone;
    private static final Integer orderRentTime;
    private static final String orderDeliveryDate;
    private static final String orderComment;

    private static final String scooterColorBlack;
    private static final String scooterColorGrey;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/config/resource.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        baseURI = properties.getProperty("baseURI");

        createCourierEndpoint = properties.getProperty("createCourierEndpoint");
        loginCourierEndpoint = properties.getProperty("loginCourierEndpoint");
        deleteCourierEndpoint = properties.getProperty("deleteCourierEndpoint");

        createOrderEndpoint = properties.getProperty("createOrderEndpoint");
        cancelOrderEndpoint = properties.getProperty("cancelOrderEndpoint");
        getOrdersEndpoint = properties.getProperty("getOrdersEndpoint");

        userLogin = properties.getProperty("courierLogin");
        userPassword = properties.getProperty("courierPassword");
        userFirstName = properties.getProperty("courierFirstName");

        wrongUserLogin = properties.getProperty("wrongCourierLogin");
        wrongUserPassword = properties.getProperty("wrongCourierPassword");

        orderFirstName = properties.getProperty("orderFirstName");
        orderLastName = properties.getProperty("orderLastName");
        orderAddress = properties.getProperty("orderAddress");
        orderMetroStation = Integer.valueOf(properties.getProperty("orderMetroStation"));
        orderPhone = properties.getProperty("orderPhone");
        orderRentTime = Integer.valueOf(properties.getProperty("orderRentTime"));
        orderDeliveryDate = properties.getProperty("orderDeliveryDate");
        orderComment = properties.getProperty("orderComment");

        scooterColorGrey = properties.getProperty("scooterColorGrey");
        scooterColorBlack = properties.getProperty("scooterColorBlack");

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

    public static String getCancelOrderEndpoint() {
        return cancelOrderEndpoint;
    }

    public static String getGetOrdersEndpoint() {
        return getOrdersEndpoint;
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


    public static String getWrongUserLogin() {
        return wrongUserLogin;
    }

    public static String getWrongUserPassword() {
        return wrongUserPassword;
    }


    public static String getOrderFirstName() {
        return orderFirstName;
    }

    public static String getOrderLastName() {
        return orderLastName;
    }

    public static String getOrderAddress() {
        return orderAddress;
    }

    public static Integer getOrderMetroStation() {
        return orderMetroStation;
    }

    public static String getOrderPhone() {
        return orderPhone;
    }

    public static Integer getOrderRentTime() {
        return orderRentTime;
    }

    public static String getOrderDeliveryDate() {
        return orderDeliveryDate;
    }

    public static String getOrderComment() {
        return orderComment;
    }


    public static String getScooterColorBlack() {
        return scooterColorBlack;
    }

    public static String getScooterColorGrey() {
        return scooterColorGrey;
    }

}
