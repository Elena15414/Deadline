package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));
    public static String testName = "vasya";
    public static String testPassword = "qwerty123";

    private DataHelper() {

    }

    public static AuthData getAuthData() {
        return new AuthData(testName, testPassword);
    }

    public static String generateRandomLogin() {
        return faker.name().username();
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthData generateRandomUser() {
        return new AuthData(generateRandomLogin(), generateRandomPassword());
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class AuthData {
        String login;
        String password;
    }
}