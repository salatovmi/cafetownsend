package managers;

import dataProvider.ConfigReader;
import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

/**
 * WebDriverManager is used for storing and sharing between steps and scenarios WebDriver
 * Driver Type, browser size and implicitly wait are initialized regarding config values
 */
public class WebDriverManager {
    private WebDriver driver;
    private BrowserType browserType;
    private ConfigReader configReader = FileReaderManager.getInstance().getConfigReader();
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String OPERA_DRIVER_PROPERTY = "webdriver.opera.driver";

    public WebDriverManager() {
        browserType = configReader.getBrowserType();
    }

    public WebDriver getDriver() {
        return driver == null ? driver = createDriver() : driver;
    }

    private WebDriver createDriver() {
        switch (browserType) {
            case FIREFOX -> {
                System.setProperty(FIREFOX_DRIVER_PROPERTY, configReader.getFirefoxDriverPath());
                driver = new FirefoxDriver();
            }
            case CHROME -> {
                System.setProperty(CHROME_DRIVER_PROPERTY, configReader.getChromeDriverPath());
                driver = new ChromeDriver();
            }
            case OPERA -> {
                System.setProperty(OPERA_DRIVER_PROPERTY, configReader.getOperaDriverPath());
                driver = new OperaDriver();
            }
        }

        if(configReader.getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configReader.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
