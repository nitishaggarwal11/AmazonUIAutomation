package stepDefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.DriverFactory;
import pages.ProductPage;

public class Product {
	
	ProductPage productPage = new ProductPage(DriverFactory.getDriver());
	
	@Then("^User navigates to the product search screen$")
	public void user_navigates_to_the_product_search_screen() throws Throwable {
		productPage.checkProductPageOpens();
	}

	@When("^User click on the Add to Cart button$")
	public void user_click_on_the_Add_to_Cart_button() throws Throwable {
		productPage.clickOnAddToCartButton();
	}
	
	@Then("^Item should be added to the cart$")
	public void item_should_be_added_to_the_cart() throws Throwable {
		productPage.checkItemAddedIntoTheCart();
	}
	
	@When("^User clicks on Cart button$")
	public void user_clicks_on_Cart_button() throws Throwable {
		productPage.clickOnCartImageButton();
	}
	
}
