package SearchByNumberTest;

import Pages.MainPage;
import Pages.NBarFines;
import driver.EmulatorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.MobileBy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchByNumberTest extends BaseTest {
    MainPage mainPage = new MainPage();
    NBarFines nBarFines = new NBarFines();
    EmulatorHelper emulatorHelper = new EmulatorHelper();
    @DisplayName("Проверка видимости кнопки 'База ГИБДД - Платно'")
    @Test
    public void paidButtonEnabledCheck(){
        mainPage.clickNBarGNumber();
        emulatorHelper.sendKeysAndFind($(id("ru.vin.proverka.auto:id/carNumberCodeEditText")),"А001АА01");
        assertTrue(mainPage.paidButtonEnabled(), "Кнопка База 'ГИБДД - Платно' неактивна");
    }
    @ParameterizedTest(name = "#{index} - проверка на валидность формата номера {0}")
    @CsvSource({"1234АА54", "А123АА54", "А123АА154"})
    @DisplayName("Проверка на валидность форматов номеров")
    public void enterValidGosNumber(String number) {
        nBarFines.nBarFinesButtonClick();
        emulatorHelper.sendKeysAndFind(nBarFines.getGosNumberEditText(), number);
        nBarFines.getErrorTextView().shouldNotBe(visible);
    }
    @ParameterizedTest(name = "#{index} - проверка на невалидность формата номера {0}")
    @CsvSource({"1А34А54", "АА123АА", "F123154"})
    @DisplayName("Проверка на невалидность форматов номеров")
    public void enterInvalidGosNumber(String number) {
        nBarFines.nBarFinesButtonClick();
        emulatorHelper.sendKeysAndFind(nBarFines.getGosNumberEditText(), number);
        nBarFines.getErrorTextView().shouldBe(visible);
    }

}
