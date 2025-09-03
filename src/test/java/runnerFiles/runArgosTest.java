package runnerFiles;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/featureFile/argos.feature"},
		glue = {"stepDefenition"},
		plugin = {"pretty", "json:target/cucumber-reports/report.json",
				"html:target/cucumber-reports/report.html"},
		monochrome = true
		)
public class runArgosTest extends AbstractTestNGCucumberTests{

}