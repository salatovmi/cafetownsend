package pageFactory;

import dataProvider.ConfigReader;
import managers.FileReaderManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * This class is the representation of Employees page
 * WebElements of page are initialized by PageFactory class
 * Class contains methods for clicking on buttons and sending values to fields
 */
public class EmployeesPage {
    WebDriver driver;
    ConfigReader configReader;
    FileReaderManager frm = FileReaderManager.getInstance();
    WebDriverWait wait;

    public EmployeesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = frm.getConfigReader();
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Logout')]")
    private WebElement buttonLogout;

    @FindBy(how = How.CSS, using = "#bAdd")
    private WebElement buttonCreate;

    @FindBy(how = How.CSS, using = "#bEdit")
    private WebElement buttonEdit;

    @FindBy(how = How.CSS, using = "#bDelete")
    private WebElement buttonDelete;

    public void clickLogoutButton() {
        waitUntilClickable(buttonLogout);
        buttonLogout.click();
    }

    public void clickCreateButton() {
        waitUntilClickable(buttonCreate);
        buttonCreate.click();
    }

    public void clickEditButton() {
        waitUntilClickable(buttonEdit);
        buttonEdit.click();
    }

    public void clickDeleteButton() {
        waitUntilClickable(buttonDelete);
        buttonDelete.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //Wait up to 5 seconds until element becomes clickable
    private void waitUntilClickable(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }

    //Method finds all created default employees and deletes it
    //Default employee is defined in config file
    public void deleteAllDefaultEmployees() {
        List<WebElement> employees = findDefaultEmployee();
        if (employees.size() == 0) return;
        for (WebElement element : employees) {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            clickDeleteButton();
        }
    }

    //Find list of Default employees
    public List<WebElement> findDefaultEmployee() {
        String employee = configReader.getEmployeeFirstName() + " " + configReader.getEmployeeLastName();
        return driver.findElements(By.xpath(String.format("//li[contains(text(),'%s')]", employee)));
    }

    //Check if exactly one default employee exists
    public boolean oneDefaultEmployeeExists() {
        return findDefaultEmployee().size() == 1;
    }

    //Check if default employee doesn't exist
    public boolean defaultEmployeeDoesNotExist() {
        return findDefaultEmployee().size() == 0;
    }

    //Open for edition first occurrence of default employee
    public void editDefaultEmployee() {
        findDefaultEmployee().get(0).click();
        clickEditButton();
    }

    //Find and delete first occurrence of default employee
    public void deleteDefaultEmployee() {
        WebElement employee = findDefaultEmployee().get(0);
        employee.click();
        clickDeleteButton();
    }
}
