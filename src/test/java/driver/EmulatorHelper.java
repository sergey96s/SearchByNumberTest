package driver;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

public class EmulatorHelper extends EmulatorDriver {

    public static void goBack() {
        driver.navigate().back();
    }

    public static void closeKeyBoard() {
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }

    public void sendKeysAndFind(SelenideElement element, String text) {
        element.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return driver;
    }
}