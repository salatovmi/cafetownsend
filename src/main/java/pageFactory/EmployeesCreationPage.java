package pageFactory;

import dataProvider.ConfigReader;
import managers.FileReaderManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is the representation of Employees creation page
 * WebElements of page are initialized by PageFactory class
 * Class contains methods for clicking on buttons and sending values to fields
 */
public class EmployeesCreationPage {
    WebDriver driver;
    ConfigReader configReader;
    FileReaderManager frm = FileReaderManager.getInstance();
    WebDriverWait wait;
    JavascriptExecutor js;
    String firstName;
    String lastName;
    String startDate;
    String email;

    public EmployeesCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = frm.getConfigReader();
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor) driver;
        firstName = configReader.getEmployeeFirstName();
        lastName = configReader.getEmployeeLastName();
        startDate = configReader.getEmployeeStartDate();
        email = configReader.getEmployeeEmail();
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.firstName']")
    private WebElement fieldFirstName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.lastName']")
    private WebElement fieldLastName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.startDate']")
    private WebElement fieldStartDate;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.email']")
    private WebElement fieldEmail;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add')]")
    private WebElement buttonAdd;

    public void clickCancelButton() {
        buttonCancel.click();
    }

    public void clickAddButton() {
        buttonAdd.click();
    }

    public void enterFirstName(String firstName) {
        fieldFirstName.clear();
        fieldFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        fieldLastName.clear();
        fieldLastName.sendKeys(lastName);
    }

    public void enterStartDate(String startDate) {
        fieldStartDate.clear();
        fieldStartDate.sendKeys(startDate);
    }

    public void enterEmail(String email) {
        fieldEmail.clear();
        fieldEmail.sendKeys(email);
    }

    public void fillInAllFields() {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterStartDate(startDate);
        enterEmail(email);
    }

    //Method checks if field is required for employee creation
    public boolean isFieldRequired(String field) {
        WebElement element = null;
        switch (field) {
            case "first name" -> element = fieldFirstName;
            case "last name" -> element = fieldLastName;
            case "start date" -> element = fieldStartDate;
            case "email" -> element = fieldEmail;
        }
        return (Boolean) js.executeScript("return arguments[0].required;", element);
    }
}
