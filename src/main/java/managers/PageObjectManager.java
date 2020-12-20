package managers;

import org.openqa.selenium.WebDriver;
import pageFactory.EmployeesCreationPage;
import pageFactory.EmployeesEditionPage;
import pageFactory.EmployeesPage;
import pageFactory.LoginPage;

/**
 * PageObjectManager stores one instance of each page representation
 * You can share one page instance between test steps and scenarios
 */
public class PageObjectManager {
    private WebDriver driver;
    private LoginPage loginPage;
    private EmployeesPage employeesPage;
    private EmployeesCreationPage employeesCreationPage;
    private EmployeesEditionPage employeesEditionPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return loginPage == null ? loginPage = new LoginPage(driver) : loginPage;
    }

    public EmployeesPage getEmployeesPage() {
        return employeesPage == null ? employeesPage = new EmployeesPage(driver) : employeesPage;
    }

    public EmployeesCreationPage getEmployeesCreationPage() {
        return employeesCreationPage == null ? employeesCreationPage = new EmployeesCreationPage(driver) : employeesCreationPage;
    }

    public EmployeesEditionPage getEmployeesEditionPage() {
        return employeesEditionPage == null ? employeesEditionPage = new EmployeesEditionPage(driver) : employeesEditionPage;
    }
}
