package pages;

import org.junit.Assert;
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

public class ProductPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private int cartCount;
	public static String productName;
	public static String productPrice;
	
	/***
	 * Constructor - initialize driver and web elements
	 * 
	 * @param driver
	 */
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		wait = new WebDriverWait(driver, Integer.parseInt(
				ApplicationProperties.getInstance().getProperty(ConfigurationProperties.EXPLICIT_WAIT_STRING)));
	}

	/***
	 * WebElements of 'Product' page
	 */

	@FindBy(how = How.XPATH, using = "//*[@resource-id = 'title_feature_div']//android.view.View")
	private WebElement ProductName;

	@FindBy(how = How.XPATH, using = "//*[@resource-id = 'imageBlock_feature_div']")
	private WebElement ProductImage;

	@FindBy(how = How.XPATH, using = "//*[@resource-id = 'atfRedesign_priceblock_priceToPay']//android.widget.EditText")
	private WebElement ProductPrice;

	@FindBy(how = How.XPATH, using = "//*[@resource-id = 'add-to-cart-button']")
	private WebElement AddToCartButton;

	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/action_bar_cart_image")
	private WebElement CartImageButton;	
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/action_bar_cart_count")
	private WebElement CartCountText;

	/***
	 * @description Method to check that Product page opens successfully
	 */
	public void checkProductPageOpens() {
		try {
			wait.until(ExpectedConditions.visibilityOf(ProductName));
			if (ProductName.isDisplayed() && ProductImage.isDisplayed()) {
				Assert.assertTrue("User is navigated to the Product page successfully", true);
				populateProductDetails();
			} else {
				Assert.assertFalse("User is not navigated to the Product page", true);
			}

		} catch (Exception e) {
			Reporter.addStepLog("Exception in Navigating to the Product page");
		}
	}
	
	/***
	 * @description Method to extract and save the Product details
	 */
	private void populateProductDetails() {
		productName = ProductName.getAttribute("text");
		productPrice = ProductPrice.getAttribute("text").replace("rupees ", "");
	}

	/***
	 * @description Method to click on the Add to Cart button
	 */
	public void clickOnAddToCartButton() {
		try {
			
			while(!CommonFunctions.checkElementPresenceWithoutWait(AddToCartButton)) {
				CommonFunctions.swipeDown(2000);
			}
			
			cartCount = Integer.parseInt(CartCountText.getAttribute("text"));
			AddToCartButton.click();
		} catch (Exception e) {
			Reporter.addStepLog("Add to cart button is not visible");
		}
	}

	/***
	 * @description Method to check that item is added into the cart
	 */
	public void checkItemAddedIntoTheCart() {
		try {
			Thread.sleep(6000);
			if (Integer.parseInt(CartCountText.getAttribute("text")) > cartCount) {
				Assert.assertTrue("Item is added to the cart", true);
			} else {
				Assert.assertFalse("Item is not Added to the cart", true);
			}
		} catch (Exception e) {
			Reporter.addStepLog("Exception while adding item to the cart");
		}
	}

	/***
	 * @description Method to click on the Cart image button
	 */
	public void clickOnCartImageButton() {
		CartImageButton.click();
	}

}
