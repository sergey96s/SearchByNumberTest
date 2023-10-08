package Pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.MobileBy.*;

public class MainPage {

private SelenideElement freeSearchButton = $(id("ru.vin.proverka.auto:id/free_search_button"));
    private SelenideElement searchFieldGosNumber = $(id("ru.vin.proverka.auto:id/carNumberCodeEditText"));
    private SelenideElement nBarGosNumber = $(xpath("//android.widget.FrameLayout[@content-desc=\"Гос. номер\"] /android.widget.FrameLayout/android.widget.ImageView"));
    private SelenideElement paidButton = $(id("ru.vin.proverka.auto:id/paid_search_button"));

    private SelenideElement nextButton = $(id("ru.vin.proverka.auto:id/nextButton"));
    private SelenideElement permissionButton = $(id("com.android.permissioncontroller:id/permission_deny_button"));


    public void clickNextButton() {
        nextButton.click();
    }
    public boolean nextButtonCheck(){
              return nextButton.isDisplayed();
    }
   public void clickNBarGNumber(){
       nBarGosNumber.click();
   }
      public boolean paidButtonEnabled(){
       return paidButton.isEnabled();
   }
   public void clickFreeSearchButton(){
        freeSearchButton.click();
   }
   public void clickPermissionButton(){
        permissionButton.click();
   }
}
