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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.EmployeesCreationPage;
import pageFactory.EmployeesPage;

//This class contains steps definition for interaction with Employees page
public class EmployeesSteps {
    StepsDataManager stepsDataManager;
    WebDriver driver;
    EmployeesPage employeesPage;
    EmployeesCreationPage employeesCreationPage;
    ExtentReportProvider extentReportProvider;
    Scenario scenario;
    ConfigReader configReader;

    public EmployeesSteps(StepsDataManager sdm) {
        stepsDataManager = sdm;
        driver = stepsDataManager.getWebDriverManager().getDriver();
        employeesPage = stepsDataManager.getPageObjectManager().getEmployeesPage();
        employeesCreationPage = stepsDataManager.getPageObjectManager().getEmployeesCreationPage();
        extentReportProvider = ExtentReportProvider.getInstance(driver);
        configReader = FileReaderManager.getInstance().getConfigReader();
    }

    @Before
    public void beforeTest(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^check that one default employee exists$")
    public void one_default_employee() {
        if (!employeesPage.oneDefaultEmployeeExists()) {
            employeesPage.deleteAllDefaultEmployees();
            employeesPage.clickCreateButton();
            employeesCreationPage.fillInAllFields();
            employeesCreationPage.clickAddButton();
        }
    }

    @When("^user clicks on logout button$")
    public void click_on_logout_button() {
        employeesPage.clickLogoutButton();
    }

    @When("^user clicks on create button$")
    public void click_on_create_button() {
        employeesPage.clickCreateButton();
    }

    @When("^default employee doesn't exist$")
    public void delete_previous() {
        employeesPage.deleteAllDefaultEmployees();
    }

    @When("^user clicks on edit button$")
    public void click_on_edit_button() {
        employeesPage.clickEditButton();
    }

    @When("^user clicks on delete button$")
    public void click_on_delete_button() {
        employeesPage.clickDeleteButton();
    }

    @When("^user edits employee$")
    public void user_edits_employee() {
        employeesPage.editDefaultEmployee();
    }

    @When("^user deletes employee$")
    public void user_deletes_employee() {
        employeesPage.deleteDefaultEmployee();
    }

    @Then("^new employee is created$")
    public void employee_is_created() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlMatches("employees$"));
        Assert.assertEquals(configReader.getApplicationUrl() + "/employees", driver.getCurrentUrl());
        Assert.assertTrue("Default user isn't created.", employeesPage.oneDefaultEmployeeExists());
    }

    @Then("^default employee is deleted$")
    public void employee_is_deleted() {
        employeesPage.clickCreateButton();
        employeesCreationPage.clickCancelButton();
        Assert.assertTrue("Default employee is still existing.", employeesPage.defaultEmployeeDoesNotExist());
    }
}
