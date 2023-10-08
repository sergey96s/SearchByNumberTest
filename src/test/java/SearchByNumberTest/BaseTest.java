package SearchByNumberTest;


import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import driver.EmulatorDriver;
import driver.EmulatorHelper;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.IOException;

import java.util.concurrent.ExecutionException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helper.DeviceHelper.executeSh;
import static helper.DriverHelper.runHelper;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    static MainPage mainPage = new MainPage();
    private AndroidDriver driver;
    public static String SCREENSHOT_TO_SAVE_FOLDER = "screenshots/actual/";

    @BeforeAll
    public static void setup() throws IOException, ExecutionException, InterruptedException {

        //SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
        Configuration.browserSize = null;
        Configuration.browser = runHelper().getDriverClass().getName();

        Configuration.timeout = 15000;

        disableAnimationOnEmulator();
    }

    private static void skipEducation() {
        mainPage.clickNextButton();
        mainPage.clickNextButton();
        mainPage.clickNextButton();
        mainPage.clickPermissionButton();

    }

    private static void disableAnimationOnEmulator() throws IOException, ExecutionException, InterruptedException {
        executeSh("adb -s shell settings put global transition_animation_scale 0.0");
        executeSh("adb -s shell settings put global window_animation_scale 0.0");
        executeSh("adb -s shell settings put global animator_duration_scale 0.0");
    }

    @BeforeEach
    public void startDriver() {
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
        skipEducation();
    }

    @AfterEach
    public void afterEach() {
        step("Закрываю приложение", Selenide::closeWebDriver);
    }


}