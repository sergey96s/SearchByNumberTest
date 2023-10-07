package Pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

private SelenideElement freeSearchButton = $(MobileBy.id("ru.vin.proverka.auto:id/free_search_button"));
    private SelenideElement searchFieldGosNumber = $(MobileBy.id("ru.vin.proverka.auto:id/carNumberCodeEditText"));
    private SelenideElement nBarGosNumber = $(MobileBy
           .xpath("//android.widget.FrameLayout[@content-desc=\"Гос. номер\"] /android.widget.FrameLayout/android.widget.ImageView"));
    private SelenideElement paidButton = $(MobileBy.id("ru.vin.proverka.auto:id/paid_search_button"));
    public void setSearchFieldGosNumber(SelenideElement searchFieldGosNumber) {
        this.searchFieldGosNumber = searchFieldGosNumber;
    }



   public void clickNBarGNumber(){
       nBarGosNumber.click();
   }
      public void paidButtonEnabled(){
       paidButton.isEnabled();
   }
   public void clickFreeSearchButton(){
        freeSearchButton.click();
   }
}
