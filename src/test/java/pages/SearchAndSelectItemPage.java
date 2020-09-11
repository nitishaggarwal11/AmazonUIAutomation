package pages;

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
import utils.Excel;

public class SearchAndSelectItemPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	/***
	 * Constructor - initialize driver and web elements 
	 * @param driver
	 */
	public SearchAndSelectItemPage (WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		wait = new WebDriverWait(driver, Integer.parseInt(ApplicationProperties.getInstance().getProperty(ConfigurationProperties.EXPLICIT_WAIT_STRING)));
	}
	
	/***
	 * WebElements of 'Search and Select Item' page
	 */
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	private WebElement SearchItemField;
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/search_right_cam_img")
	private WebElement SearchThroughImage;
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions")
	private WebElement ItemSuggestion;
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/rs_results_count_text")
	private WebElement NumberOfResultsText;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id = 'com.amazon.mShop.android.shopping:id/rs_vertical_stack_view']//android.widget.LinearLayout[3]")
	private WebElement ItemToSelect;
	
	/***
	 * @description Method to Search an Item and select it from the suggestion list
	 */
	public void searchItem() {
		try {
			wait.until(ExpectedConditions.visibilityOf(SearchItemField));
			SearchItemField.click();
			SearchItemField.clear();
			SearchItemField.sendKeys(Excel.getData().get("Product"));
			
			wait.until(ExpectedConditions.visibilityOf(ItemSuggestion));
			ItemSuggestion.click();
		} catch (Exception e) {
			Reporter.addStepLog("Exception in Searching the item");
		}
	}
	
	/***
	 * @description Method to Select an Item (Not First/Last) from the searched suggestions 
	 */
	public void selectItem() {
		wait.until(ExpectedConditions.visibilityOf(ItemToSelect));
		CommonFunctions.swipeDown(2000);
		ItemToSelect.click();
	}

}
