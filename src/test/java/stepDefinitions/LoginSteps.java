package stepDefinitions;

import dataProvider.ConfigReader;
import dataProvider.ExtentReportProvider;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import managers.StepsDataManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.LoginPage;

//This class contains steps definitions for interaction with Login page
public class LoginSteps {
    StepsDataManager stepsDataManager;
    WebDriver driver;
    LoginPage loginPage;
    ExtentReportProvider extentReportProvider;
    Scenario scenario;
    ConfigReader configReader;

    public LoginSteps(StepsDataManager sdm) {
        stepsDataManager = sdm;
        driver = stepsDataManager.getWebDriverManager().getDriver();
        loginPage = stepsDataManager.getPageObjectManager().getLoginPage();
        extentReportProvider = ExtentReportProvider.getInstance(driver);
        configReader = FileReaderManager.getInstance().getConfigReader();
    }

    @Before
    public void beforeTest(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^user is on Login page$")
    public void user_is_on_Login_page() {
        loginPage.openLoginPage();
        extentReportProvider.addStepLog("Login page is opened");
        extentReportProvider.addScreenshotToReport(scenario);
    }

    @Given("^user is logged in$")
    public void user_is_logged_in() {
        loginPage.openLoginPage();
        loginPage.loginAction(configReader.getUsername(), configReader.getPassword());
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("employees"));
    }

    @When("^user enters Username (.+)$")
    public void use_enter_Username(String username) {
        if (username.equalsIgnoreCase("empty")) username = "";
        else if (username.equalsIgnoreCase("valid")) username = configReader.getUsername();
        loginPage.enterUsername(username);
    }

    @When("^user enters Password (.+)$")
    public void use_enter_Password(String password) {
        if (password.equalsIgnoreCase("empty")) password = "";
        else if (password.equalsIgnoreCase("valid")) password = configReader.getPassword();
        loginPage.enterPassword(password);
    }

    @When("^click on Login button")
    public void click_on_Login_button() {
        loginPage.clickLoginButton();
    }

    @Then("^login (.+)$")
    public void login_result(String result) {
        if (result.equalsIgnoreCase("successful")) {
            new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("employees"));
            WebElement greetings = driver.findElement(By.xpath("//p[@id='greetings']"));
            Assert.assertTrue("Login failed. Greeting message is not displayed.", greetings.isDisplayed());
        } else if (result.equalsIgnoreCase("failed")) {
            WebElement failedLogin = driver.findElement(By.xpath("//p[@class='error-message ng-binding']"));
            Assert.assertTrue("Error message is not displayed.", failedLogin.isDisplayed());
            Assert.assertEquals("Invalid username or password!", failedLogin.getText());
        } else if (result.equalsIgnoreCase("blocked")) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue("User redirected to " + currentUrl, currentUrl.contains("login"));
            Assert.assertTrue("Username isn't required field.", loginPage.isUsernameRequired());
            Assert.assertTrue("Password isn't required field.", loginPage.isPasswordRequired());
        }
    }

    @Then("^password is hidden with asterisks$")
    public void password_is_hidden() {
        Assert.assertTrue("Password isn't hidden.", loginPage.isPasswordHidden());
    }

    @Then("^user is still logged in$")
    public void user_is_still_logged_in() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse("User redirected to " + currentUrl, currentUrl.contains("login"));
    }

    @Then("^user is redirected to Login page$")
    public void user_redirected_to_Login_page() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("User redirected to " + currentUrl, currentUrl.contains("login"));
    }

    @Then("^username and password fields are empty$")
    public void fields_are_empty() {
        Assert.assertTrue("Username field isn't empty", loginPage.isUsernameFieldEmpty());
        Assert.assertTrue("Password field isn't empty", loginPage.isPasswordFieldEmpty());
    }
}
