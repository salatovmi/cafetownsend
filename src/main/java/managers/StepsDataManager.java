package managers;

/**
 * StepsDataManager stores and provides exactly one instance of each manager
 * It is possible by using PicoContainer
 * Instance of StepsDataManager is sent as parameter to Steps constructors
 */
public class StepsDataManager {
    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;

    public StepsDataManager() {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
