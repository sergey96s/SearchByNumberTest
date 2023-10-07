package helper;

import Config.ConfigReader;
import driver.EmulatorDriver;


public class DriverHelper {

    private DriverHelper() {
    }

    public static DriverHelper runHelper() {
        return new DriverHelper();
    }

    public Class<?> getDriverClass() {
        String deviceHost = ConfigReader.testConfig.deviceHost();

        switch (deviceHost) {
            case "browserstack":
            case "selenoid":
            case "emulator":
                return EmulatorDriver.class;
            case "real":
            default:
                throw new RuntimeException("В файле конфигурации нет параметра deviceHost: " +
                        "browserstack/selenoid/emulator/real");
        }
    }
}