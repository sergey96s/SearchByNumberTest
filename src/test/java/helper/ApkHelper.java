package helper;

import Config.ConfigReader;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.DeviceHelper.executeSh;

public class ApkHelper {

    private String apkInfo;

    public ApkHelper() {
        String app = ConfigReader.emulatorConfig.app();
        if (app == null || app.isEmpty()) {
            throw new RuntimeException("No value for key 'app' providing apk path in emulator.properties");
        }
        try {
            String pathToAn = "C:\\Users\\serzh\\AppData\\Local\\Android\\Sdk\\build-tools\\34.0.0\\";
            apkInfo = executeSh(pathToAn + "  " + ConfigReader.emulatorConfig.app());
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAppPackageFromApk() {
        return findGroup1ValueFromString(apkInfo, "package: name='\\s*([^']+?)\\s*'");
    }

    public String getAppMainActivity() {
        return findGroup1ValueFromString(apkInfo, "launchable-activity: name='\\s*([^']+?)\\s*'");
    }

    private static String findGroup1ValueFromString(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}