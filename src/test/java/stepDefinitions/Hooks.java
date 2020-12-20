package stepDefinitions;

import dataProvider.ExtentReportProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.StepsDataManager;

//This class defines Before and After hooks
//After each scenario it makes screenshot and adds to the report
//and closes WebDriver
public class Hooks {
    StepsDataManager stepsDataManager;
    ExtentReportProvider extentReportProvider;

    public Hooks(StepsDataManager sdm) {
        stepsDataManager = sdm;
        extentReportProvider = ExtentReportProvider.getInstance(sdm.getWebDriverManager().getDriver());
    }

    @Before
    public void BeforeScenario() {
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        extentReportProvider.addScreenshotToReport(scenario);
    }

    @After(order = 0)
    public void AfterScenario() {
        stepsDataManager.getWebDriverManager().closeDriver();
    }
}
