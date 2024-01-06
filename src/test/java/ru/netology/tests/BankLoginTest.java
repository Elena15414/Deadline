package ru.netology.tests;

import org.junit.jupiter.api.*;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.getAuthData;
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

}