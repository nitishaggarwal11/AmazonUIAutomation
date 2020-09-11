package utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import driver.DriverFactory;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonFunctions {
	
	/***
	 * @description Method to get the boolean based on element is Present on screen or not
	 * @param element
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkElementPresenceWithWait(WebElement element, int waitInsec, int poolingFreq)
	{
		try {
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver())
				       .withTimeout(waitInsec, TimeUnit.SECONDS)
				       .pollingEvery(poolingFreq, TimeUnit.SECONDS);
			
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/***
	 * @description Method to check Element presence without wait
	 * @param element
	 * @return
	 */
	public static boolean checkElementPresenceWithoutWait(WebElement element)
	{
		return checkElementPresenceWithWait(element, 10, 5);
	}

	@SuppressWarnings("rawtypes")
	public static void swipeDown(int wait)
	{
		AndroidDriver  driver = (AndroidDriver )DriverFactory.getDriver();
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int startY = (int)(height*0.7);
		int endY = (int)(height*0.3);
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(wait)))
		.moveTo(PointOption.point(x, endY)).release().perform();
	}
	
}
