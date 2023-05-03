package io.qameta.junit5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class WebRemoteTest {
    static String baseUrlUI;


    @BeforeAll
    public static void setUpAll() {
        // baseUrl = new SetupFunctions().getBaseUrlUI();
        baseUrlUI = "http://51.250.6.164:3000";

        Configuration.remote = "http://51.250.6.164:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserVersion = "111.0";

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));

    }

    @BeforeEach
    public void setup() {
        System.out.println("Trying to open browsers");
        open(baseUrlUI);
        System.out.println("browser Opened OK");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void incorrectLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.insertLogin("hello");
        loginPage.insertPassword("wrongpasswwoorrd");
        loginPage.clickLogin();
        loginPage.checkErrorPopup();
    }


}
