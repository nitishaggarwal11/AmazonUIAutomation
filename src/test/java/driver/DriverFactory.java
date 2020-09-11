package driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import config.ApplicationProperties;
import constants.ConfigurationProperties;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	/***
	 * Constructor
	 */
	public DriverFactory() 
	{
		System.out.println("In Driver Factory Class");
	}
	
	/***
	 * @description Function to Initialize the driver
	 */
	@SuppressWarnings("rawtypes")
	public static void initDriver(String impliciWaitString) 
	{
		try {
			File amazonApp = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Amazon_shopping.apk");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("noReset", false);
			capabilities.setCapability("deviceName", ApplicationProperties.getInstance().getProperty(ConfigurationProperties.DEVICE_NAME));
			capabilities.setCapability("platformVersion", ApplicationProperties.getInstance().getProperty(ConfigurationProperties.PLATFORM_VERSION));
			capabilities.setCapability("platformName", ApplicationProperties.getInstance().getProperty(ConfigurationProperties.PLATFORM_NAME));
			capabilities.setCapability("app", amazonApp.getAbsolutePath());
			capabilities.setCapability("appPackage", ApplicationProperties.getInstance().getProperty(ConfigurationProperties.APP_PACKAGE));
			capabilities.setCapability("appActivity", ApplicationProperties.getInstance().getProperty(ConfigurationProperties.APP_ACTIVITY));
			
			driver = new AndroidDriver(new URL(ApplicationProperties.getInstance().getProperty(ConfigurationProperties.URL)), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(impliciWaitString), TimeUnit.SECONDS);
	}
	
	/***
	 * @description Function to get the Driver instance 
	 * @return
	 */
	public static WebDriver getDriver() {
		return driver;
	}
	
}
