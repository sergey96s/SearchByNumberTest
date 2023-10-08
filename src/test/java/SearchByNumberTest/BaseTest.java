package SearchByNumberTest;


import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import java.util.concurrent.ExecutionException;


import static com.codeborne.selenide.Selenide.$;
import static helper.DeviceHelper.executeSh;
import static helper.DriverHelper.runHelper;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    MainPage mainPage = new MainPage();
   private AndroidDriver driver;
    public static String SCREENSHOT_TO_SAVE_FOLDER = "screenshots/actual/";

    @BeforeAll
    public static void setup() throws IOException, ExecutionException, InterruptedException {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.browserSize = null;
        Configuration.timeout = 35000;
        disableAnimationOnEmulator();
    }

    @BeforeAll
    public void skipEducation(){
        while(mainPage.nextButtonCheck()){
            mainPage.clickNextButton();{
                break;
            }
        }
    }

    private static void disableAnimationOnEmulator() throws IOException, ExecutionException, InterruptedException {
        executeSh("adb -s shell settings put global transition_animation_scale 0.0");
        executeSh("adb -s shell settings put global window_animation_scale 0.0");
        executeSh("adb -s shell settings put global animator_duration_scale 0.0");
    }

    @BeforeEach
    public void startDriver() {
        step("Открываю приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    @AfterEach
    public void afterEach() {
        step("Закрываю приложение", Selenide::closeWebDriver);
    }


}