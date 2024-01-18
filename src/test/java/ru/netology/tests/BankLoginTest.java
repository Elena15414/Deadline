package ru.netology.tests;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.generateRandomUser;
import static ru.netology.data.SQLHelper.*;

public class BankLoginTest {
    LoginPage loginPage;


    @AfterEach
    void cleanCode() {
        cleanAuthCode();
    }

    @AfterAll
    static void cleanAll() {
        cleanDataBase();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Should successfully login with registered user and right code")
    void shouldPositiveLogin() {
        var verificationPage = loginPage.validLogin(getAuthData());
        verificationPage.verificationPageIsVisible();
        verificationPage.validVerify(String.valueOf(getVerificationCode()));
    }
    @Test
    @DisplayName("Should get error when invalid login")
    void shouldNotLoginWithInvalidLogin() {
        loginPage.validLogin(new DataHelper.AuthData(testName, generateRandomPassword()));
        loginPage.findErrorMessage("Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error when invalid password")
    void shouldNotLoginWithInvalidPassword() {
        loginPage.validLogin(new DataHelper.AuthData(generateRandomLogin(), testPassword));
        loginPage.findErrorMessage("Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error when invalid login and password")
    void shouldNotLoginWithInvalidLoginAndPassword() {
        loginPage.validLogin(generateRandomUser());
        loginPage.findErrorMessage("Неверно указан логин или пароль");
    }

}