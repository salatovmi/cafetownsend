package stepDefinitions;

import dataProvider.ConfigReader;
import dataProvider.ExtentReportProvider;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import managers.StepsDataManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageFactory.EmployeesCreationPage;

//This class contains steps definition for interaction with Employees creation page
public class EmployeesCreationSteps {
    StepsDataManager stepsDataManager;
    WebDriver driver;
    EmployeesCreationPage employeesCreationPage;
    ExtentReportProvider extentReportProvider;
    Scenario scenario;
    ConfigReader configReader;

    public EmployeesCreationSteps(StepsDataManager sdm) {
        stepsDataManager = sdm;
        driver = stepsDataManager.getWebDriverManager().getDriver();
        employeesCreationPage = stepsDataManager.getPageObjectManager().getEmployeesCreationPage();
        extentReportProvider = ExtentReportProvider.getInstance(driver);
        configReader = FileReaderManager.getInstance().getConfigReader();
    }

    @Before
    public void beforeTest(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("^user fills in all fields$")
    public void user_fills_in_all_fields() {
        employeesCreationPage.fillInAllFields();
    }

    @When("^user leaves (.+) empty$")
    public void leave_field_empty(String field) {
        switch (field.toLowerCase()) {
            case "first name" -> employeesCreationPage.enterFirstName("");
            case "last name" -> employeesCreationPage.enterLastName("");
            case "start date" -> employeesCreationPage.enterStartDate("");
            case "email" -> employeesCreationPage.enterEmail("");
        }
    }

    @When("^user clicks on add button$")
    public void user_clicks_on_add_button() {
        employeesCreationPage.clickAddButton();
    }

    @When("^user creates new employee$")
    public void user_creates_new_employee() {
        employeesCreationPage.fillInAllFields();
        employeesCreationPage.clickAddButton();
    }

    @Then("employee isn't created")
    public void employee_is_not_created() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("User redirected to " + currentUrl, currentUrl.contains("new"));
    }

    @Then("^field (.+) is required$")
    public void field_is_required(String field) {
        Assert.assertTrue(String.format("Field %s isn't required.", field), employeesCreationPage.isFieldRequired(field.toLowerCase()));
    }
}
