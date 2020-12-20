package stepDefinitions;

import io.cucumber.java.en.When;
import managers.StepsDataManager;
import org.openqa.selenium.WebDriver;

//This class contains common steps for different pages
public class CommonSteps {
    StepsDataManager stepsDataManager;
    WebDriver driver;

    public CommonSteps(StepsDataManager sdm) {
        stepsDataManager = sdm;
        driver = stepsDataManager.getWebDriverManager().getDriver();
    }

    @When("^user clicks on back button$")
    public void click_back_button() {
        driver.navigate().back();
    }
}
