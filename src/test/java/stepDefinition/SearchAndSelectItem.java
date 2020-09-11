package stepDefinition;

import cucumber.api.java.en.When;
import driver.DriverFactory;
import pages.ProductPage;
import pages.SearchAndSelectItemPage;

public class SearchAndSelectItem {
	
	SearchAndSelectItemPage searchAndSelectItemPage = new SearchAndSelectItemPage(DriverFactory.getDriver());
	ProductPage page = new ProductPage(DriverFactory.getDriver());
	
	@When("^User searches for an item$")
	public void user_searches_for_an_item() {
		searchAndSelectItemPage.searchItem();
	}

	@When("^Select any random item from the list$")
	public void select_any_random_item_from_the_list() {
		searchAndSelectItemPage.selectItem();
	}
	
}
