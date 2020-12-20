package stepDefinitions;

import dataProvider.ExtentReportProvider;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.StepsDataManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageFactory.EmployeesEditionPage;
import pageFactory.EmployeesPage;

//This class contains steps definition for interaction with Employees edition page
public class EmployeesEditionSteps {
    StepsDataManager stepsDataManager;
    WebDriver driver;
    EmployeesEditionPage employeesEditionPage;
    EmployeesPage employeesPage;
    ExtentReportProvider extentReportProvider;
    Scenario scenario;

    public EmployeesEditionSteps(StepsDataManager sdm) {
        stepsDataManager = sdm;
        driver = stepsDataManager.getWebDriverManager().getDriver();
        employeesEditionPage = stepsDataManager.getPageObjectManager().getEmployeesEditionPage();
        employeesPage = stepsDataManager.getPageObjectManager().getEmployeesPage();
        extentReportProvider = ExtentReportProvider.getInstance(driver);
    }

    @Before
    public void beforeTest(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("^user changes employee's email to (.+)$")
    public void change_employee_email(String email) {
        employeesEditionPage.enterEmail(email);
        employeesEditionPage.clickUpdateButton();
        employeesEditionPage.clickBackButton();
    }

    @Then("^employee's email equals (.+)$")
    public void employee_email_equal(String email) {
        employeesPage.findDefaultEmployee().get(0).click();
        employeesPage.clickEditButton();
        Assert.assertEquals(email + " " + employeesEditionPage.getEmailValue() + " are different.",
                email, employeesEditionPage.getEmailValue());
    }
}
