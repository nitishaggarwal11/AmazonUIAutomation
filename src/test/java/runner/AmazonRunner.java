package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import config.ApplicationProperties;
import constants.ConfigurationProperties;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import driver.DriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "./src/test/resources/features/",
		glue = {"stepDefinition"},
		monochrome = false,
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html"},
		tags = {"@AmazonTest"}		
		)

public class AmazonRunner 
{
	@BeforeClass
	public static void initEnvironment() {
		String implicitWaitString = ApplicationProperties.getInstance().getProperty(ConfigurationProperties.IMPLICIT_WAIT_STRING);
		DriverFactory.initDriver(implicitWaitString);
	}
	
	@AfterClass
	 public static void tearDownEnvironment() {
		// Write to Extent Report
		 writeExtentReport();

		// Close Browser 
		DriverFactory.getDriver().quit();
	 }
	 
	 public static void writeExtentReport() {
		 Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + File.separator + ApplicationProperties.getInstance().getProperty(ConfigurationProperties.REPORT_CONFIG_NAME)));
		 Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		 Reporter.setSystemInfo("Application Name", "Amazon");
		 Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
		 Reporter.setSystemInfo("Environment", "Test");
		 Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
	 }
}
