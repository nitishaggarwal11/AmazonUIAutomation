package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import config.ApplicationProperties;
import constants.ConfigurationProperties;
import utils.CommonFunctions;

public class CheckoutPage {

	private WebDriver driver;
	private WebDriverWait wait;

	/***
	 * Constructor - initialize driver and web elements
	 * 
	 * @param driver
	 */
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		wait = new WebDriverWait(driver, Integer.parseInt(
				ApplicationProperties.getInstance().getProperty(ConfigurationProperties.EXPLICIT_WAIT_STRING)));
	}

	/***
	 * WebElements of 'Checkout' page
	 */

	@FindBy(how = How.XPATH, using = "//*[contains(@text,'Proceed to Buy')]")
	private WebElement ProceedToBuyButton;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='activeCartViewForm']//android.view.View//android.view.View[2]")
	private WebElement ProductDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='activeCartViewForm']//android.view.View//android.view.View[2]//android.view.View[1]")
	private WebElement ProductName;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='activeCartViewForm']//android.view.View//android.view.View[2]//android.widget.ListView//android.view.View")
	private WebElement ProductPrice;

	/***
	 * @description Method to check that Checkout page opens successfully
	 */
	public void verifyCheckoutPageOpens() {
		try {
			wait.until(ExpectedConditions.visibilityOf(ProceedToBuyButton));
			if (ProceedToBuyButton.isDisplayed()) {
				Assert.assertTrue("User is navigated to the Checkout page successfully", true);
			} else {
				Assert.assertFalse("User is not navigated to the Checkout page", true);
			}
		} catch (Exception e) {
			Reporter.addStepLog("Exception in Navigating to the Checkout page");
		}
	}

	/***
	 * @description Method to verify the product details on checkout page
	 */
	public void verifyProductDetails(String expectedProductName, String expectedProductPrice) {
		try {
			WebElement ProductName = driver.findElement(By.xpath("//*[contains(@text,'" + expectedProductName + "')]"));
			WebElement ProductPrice = driver.findElement(By.xpath("//*[contains(@text,'" + expectedProductPrice + "')]"));
			
			while(!CommonFunctions.checkElementPresenceWithoutWait(ProductName)) {
				CommonFunctions.swipeDown(2000);
			}
			
			if (ProductName.isDisplayed() && ProductPrice.isDisplayed()) {
				Assert.assertTrue("Product details are same on the checkout page", true);
			} else {
				Assert.assertFalse("Product details are not same on the checkout page", true);
			}
		} catch (Exception e) {
			Reporter.addStepLog("Exception in verifying the product details on the checkout page");
		}
	}

	/***
	 * @description Method to click on the Cart image button
	 */
	public void clickOnProceedToBuyButton() {
		ProceedToBuyButton.click();
	}

}
