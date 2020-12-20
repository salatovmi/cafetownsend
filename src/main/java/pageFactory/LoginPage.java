package pageFactory;

import dataProvider.ConfigReader;
import managers.FileReaderManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is the representation of Login page
 * WebElements of page are initialized by PageFactory class
 * Class contains methods for clicking on buttons and sending values to fields
 */

public class LoginPage {
    WebDriver driver;
    ConfigReader configReader;
    FileReaderManager frm = FileReaderManager.getInstance();
    JavascriptExecutor js;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = frm.getConfigReader();
        js = (JavascriptExecutor) driver;
    }

    @FindBy(how = How.XPATH, using = "//input[@ng-model=\"user.name\"]")
    private WebElement fieldUsername;

    @FindBy(how = How.XPATH, using = "//input[@type=\"password\"]")
    private WebElement fieldPassword;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Login')]")
    private WebElement buttonLogin;

    public void enterUsername(String username) {
        fieldUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        fieldPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        buttonLogin.click();
    }

    //Set username and password fields and click on login button
    public void loginAction(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Open default url from config file
    public void openLoginPage() {
        driver.get(configReader.getApplicationUrl());
    }

    //Check if username field is required for login
    public boolean isUsernameRequired() {
        return (Boolean) js.executeScript("return arguments[0].required;", fieldUsername);
    }

    //Check if password field is required for login
    public boolean isPasswordRequired() {
        return (Boolean) js.executeScript("return arguments[0].required;", fieldPassword);
    }

    //Check if password is hidden with asterisks
    public boolean isPasswordHidden() {
        return fieldPassword.getAttribute("type").equals("password");
    }

    public boolean isUsernameFieldEmpty() {
        return fieldUsername.getAttribute("value").isEmpty();
    }

    public boolean isPasswordFieldEmpty() {
        return fieldPassword.getAttribute("value").isEmpty();
    }
}
