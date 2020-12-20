package dataProvider;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 ExtentReportProvider is used for extending test report with more convenient html report
 and adding to it screenshots.
 Structure of report is defined in src/test/resources/extent-config.xml
 Folder for saving report, name of it or folder with screenshots
 can be changed in src/test/resources/extent.properties
 */
public class ExtentReportProvider {
    private static ExtentReportProvider extentReportProvider;
    private static WebDriver driver;
    public static int counter = 1;

    private ExtentReportProvider(WebDriver driver) {
        ExtentReportProvider.driver = driver;
    }

    public static ExtentReportProvider getInstance(WebDriver driver) {
        if(extentReportProvider != null) {
            ExtentReportProvider.driver = driver;
            return extentReportProvider;
        }
        return extentReportProvider = new ExtentReportProvider(driver);
    }

    public void addScreenshotToReport(Scenario scenario) {
        //Takes a screenshot from the driver and save it to the specified location when page is ready
        new WebDriverWait(driver, 1).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Path sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();

        //Building up the destination path for the screenshot to save
        //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
        String folderPath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
        Path destinationPath = Paths.get(folderPath + "embedded" + counter + ".png");
        try {
            //Copy taken screenshot from source location to destination location
            Files.createDirectories(Paths.get(folderPath));
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            //Method attaches the specified screenshot to the test
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.toAbsolutePath().toString());
            scenario.attach(Files.readAllBytes(destinationPath), "image/png", "");
        } catch (IOException e) {
            ExtentCucumberAdapter.addTestStepLog(e.getMessage());
        }
        counter++;
    }

    //Add additional message to the test step
    public void addStepLog(String message) {
        ExtentCucumberAdapter.addTestStepLog(message);
    }
}
