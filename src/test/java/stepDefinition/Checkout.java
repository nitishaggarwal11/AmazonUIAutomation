package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import driver.DriverFactory;
import pages.CheckoutPage;
import pages.ProductPage;

public class Checkout {
	
	CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
	
	@Then("^User navigates to the Checkout screen$")
	public void user_navigates_to_the_Checkout_screen() throws Throwable {
		checkoutPage.verifyCheckoutPageOpens();
	}

	@And("^Information of product should be same on checkout screen$")
	public void information_of_product_should_be_same_on_checkout_screen() throws Throwable {
		checkoutPage.verifyProductDetails(ProductPage.productName, ProductPage.productPrice);
	}
	
}
