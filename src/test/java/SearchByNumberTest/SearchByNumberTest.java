package SearchByNumberTest;

import Pages.MainPage;
import Pages.NBarFines;
import driver.EmulatorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.MobileBy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchByNumberTest extends BaseTest {
    MainPage mainPage = new MainPage();
    NBarFines nBarFines = new NBarFines();
    EmulatorHelper emulatorHelper = new EmulatorHelper();
    @DisplayName("Проверка видимости кнопки 'База ГИБДД - Платно'")
    @Test
    public void paidButtonEnabledCheck(){
        mainPage.clickNBarGNumber();
        emulatorHelper.sendKeysAndFind($(id("ru.vin.proverka.auto:id/carNumberCodeEditText")),"А001АА01");
        Assertions.assertTrue(mainPage.paidButtonEnabled(), "Кнопка База 'ГИБДД - Платно' неактивна");
    }
    @ParameterizedTest(name = "#{index} - проверка на валидность формата номера {0}")
    @CsvSource({"1234АА54", "А123АА54", "А123АА154"})
    @DisplayName("Проверка на валидность форматов номеров")
    public void enterInvalidBussinesEmail(String number) {
        emulatorHelper.sendKeysAndFind(nBarFines.setGosNumberEditText(String), number);
        assertEquals(!nBarFines.getErrorTextView().isDisplayed(), "Введите гос.номер полностью с кодом региона. Например: В234ЕУ77"
                , "Сообщение о необходимости ввести корректный номер получено");
    }
    @ParameterizedTest(name = "#{index} - проверка на невалидность формата номера {0}")
    @CsvSource({"1А34АА54", "АА123АА5", "F123RЦ154"})
    @DisplayName("Проверка на невалидность форматов номеров")
    public void enterInvalidBussinesEmail(String number) {
        emulatorHelper.sendKeysAndFind(nBarFines.setGosNumberEditText(), number);
        assertEquals(nBarFines.getErrorTextView(), "Введите гос.номер полностью с кодом региона. Например: В234ЕУ77"
                , "Сообщение о необходимости ввести корректный номер не получено");
    }

}
