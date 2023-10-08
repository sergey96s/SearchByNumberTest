package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.id;

public class NBarFines {
    private SelenideElement nBarFinesButton = $x("//android.widget.FrameLayout[@content-desc=\"Штрафы\"]/android.widget.FrameLayout/android.widget.ImageView");
    private SelenideElement gosNumberEditText = $(id("ru.vin.proverka.auto:id/gosNumberEditText"));
    private SelenideElement stsCodeEditText = $(id("ru.vin.proverka.auto:id/stsCodeEditText"));
    private SelenideElement checkButton = $(id("ru.vin.proverka.auto:id/checkButton"));

    private SelenideElement errorTextView = $(id("ru.vin.proverka.auto:id/errorTextView"));

    public SelenideElement getErrorTextView() {
        return errorTextView;
    }

    public void setGosNumberEditText(SelenideElement gosNumberEditText) {
        this.gosNumberEditText = gosNumberEditText;
    }


    public void setStsCodeEditText(SelenideElement stsCodeEditText) {
        this.stsCodeEditText = stsCodeEditText;
    }

    public void setCheckButton(SelenideElement checkButton) {
        this.checkButton = checkButton;
    }

    public void nBarFinesButtonClick() {
        nBarFinesButton.click();
    }


    public SelenideElement getGosNumberEditText() {
        return gosNumberEditText;
    }
}
