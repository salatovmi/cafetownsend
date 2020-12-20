Cafetownsend BDD tests
=========================

### RUN

You can launch tests within your IDE, by running the TestRunner class at `src/test/java/runners/TestRunner.java`.
For selective run you can add to CucumberOptions `tags= "@Login"` or another tag.
If you prefer to use Maven, you can also run your application by typing:
`mvn test` or `mvn test -Dcucumber.filter.tags="@Login"` for selective run.

Available tags: 

@Login - check main login functionality.

@Employees - check creation, edition and deletion main functionality.

### REPORT

Report will be saved at `target/cucumber-reports/ExtentReport.html`.
It will contain results for each step and scenario, statistic and screenshots of application after each scenario.
Report could be configured by changing `src/test/resources/extent-config.xml`.
Report folder and name could be changed in `src/test/resources/extent.properties`.

URL, username, password and other properties for running could be changed at `configs/config.properties`.
Folder `src/main/resources` contains drivers for different browsers.
