package SearchByNumberTest;

import Pages.MainPage;
import driver.EmulatorHelper;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class SearchByNumberTest extends BaseTest {
    MainPage mainPage = new MainPage();
    EmulatorHelper emulatorHelper = new EmulatorHelper();
    @DisplayName("Проверка видимости кнопки 'База ГИБДД - Платно'")
    @Test
    public void paidButtonEnabledCheck(){
        mainPage.clickNBarGNumber();
        emulatorHelper.sendKeysAndFind($(MobileBy
                .id("ru.vin.proverka.auto:id/carNumberCodeEditText")),"А001АА01");
        mainPage.paidButtonEnabled();
    }



}
