package driver;

import Config.ConfigReader;
import com.codeborne.selenide.WebDriverProvider;


import helper.ApkHelper;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class EmulatorDriver implements WebDriverProvider {
    protected static AndroidDriver driver;
    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String APP = ConfigReader.emulatorConfig.app();
    private static final String URL = ConfigReader.emulatorConfig.remoteURL();

    public static URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");
        return file.getAbsolutePath();
    }

    private void initPackageAndActivity() {
        ApkHelper helper = new ApkHelper();
        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }


    @Nonnull
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        initPackageAndActivity();
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);

        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);

        desiredCapabilities.setCapability("app", getAbsolutePath(APP));
        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        return driver;
    }

//    @Nonnull
//    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
//        return driver;
//    }
}