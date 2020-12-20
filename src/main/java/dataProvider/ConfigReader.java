package dataProvider;

import enums.BrowserType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

/**
 * ConfigReader class is used for config initialization.
 * It contains methods for getting all properties from config file.
 */
public class ConfigReader {
    private Properties properties;
    private final String propertyFilePath = "configs/config.properties";


    public ConfigReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getChromeDriverPath() {
        String driverPath = properties.getProperty("chromeDriver");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("chromeDriver not specified in the config.properties file.");
    }

    public String getOperaDriverPath() {
        String driverPath = properties.getProperty("operaDriver");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("operaDriver not specified in the config.properties file.");
    }

    public String getFirefoxDriverPath() {
        String driverPath = properties.getProperty("firefoxDriver");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("firefoxDriver not specified in the config.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("cafeUrl");
        if (url != null) return url;
        else throw new RuntimeException("cafeUrl not specified in the config.properties file.");
    }

    public BrowserType getBrowserType() {
        String browserType = properties.getProperty("browser");
        if (browserType == null || browserType.equalsIgnoreCase("chrome")) return BrowserType.CHROME;
        else if (browserType.equalsIgnoreCase("opera")) return BrowserType.OPERA;
        else if (browserType.equalsIgnoreCase("firefox")) return BrowserType.FIREFOX;
        else throw new RuntimeException("Unknown browser name in config.properties: " + browserType);
    }

    public boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        return windowSize == null || Boolean.parseBoolean(windowSize);
    }

    public String getUsername() {
        String username = properties.getProperty("username");
        if (username != null) return username;
        else throw new RuntimeException("username not specified in the config.properties file.");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password not specified in the config.properties file.");
    }

    public String getEmployeeFirstName() {
        String employeeFirstName = properties.getProperty("employeeFirstName");
        if (employeeFirstName != null) return employeeFirstName;
        return "John";
    }

    public String getEmployeeLastName() {
        String employeeLastName = properties.getProperty("employeeLastName");
        if (employeeLastName != null) return employeeLastName;
        return "Doe";
    }

    public String getEmployeeStartDate() {
        String employeeStartDate = properties.getProperty("employeeStartDate");
        if (employeeStartDate != null && employeeStartDate.equals("today")) return LocalDate.now().toString();
        else if (employeeStartDate != null) return employeeStartDate;
        return "2000-01-01";
    }

    public String getEmployeeEmail() {
        String employeeEmail = properties.getProperty("employeeEmail");
        if (employeeEmail != null) return employeeEmail;
        return "john@doe.com";
    }
}
