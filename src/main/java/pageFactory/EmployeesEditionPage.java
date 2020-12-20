package pageFactory;

import dataProvider.ConfigReader;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is the representation of Employee's edition page
 * WebElements of page are initialized by PageFactory class
 * Class contains methods for clicking on buttons and sending values to fields
 */
public class EmployeesEditionPage {
    WebDriver driver;
    ConfigReader configReader;
    FileReaderManager frm = FileReaderManager.getInstance();
    WebDriverWait wait;

    public EmployeesEditionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = frm.getConfigReader();
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Back')]")
    private WebElement buttonBack;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.firstName']")
    private WebElement fieldFirstName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.lastName']")
    private WebElement fieldLastName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.startDate']")
    private WebElement fieldStartDate;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.email']")
    private WebElement fieldEmail;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Update')]")
    private WebElement buttonUpdate;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
    private WebElement buttonDelete;

    public void clickBackButton() {
        buttonBack.click();
    }

    public void clickUpdateButton() {
        buttonUpdate.click();
    }

    public void clickDeleteButton() {
        buttonDelete.click();
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

    public String getFirstNameValue() {
        return fieldFirstName.getAttribute("value");
    }

    public String getLastNameValue() {
        return fieldLastName.getAttribute("value");
    }

    public String getEmailValue() {
        return fieldEmail.getAttribute("value");
    }

    public String getStartDateValue() {
        return fieldStartDate.getAttribute("value");
    }
}
