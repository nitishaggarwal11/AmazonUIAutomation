package stepDefinition;

import cucumber.api.java.en.Given;
import driver.DriverFactory;
import pages.LoginPage;

public class Login {
	
	LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	
	@Given("^User launches the Amazon app and Login into it$")
	public void user_launches_the_Amazon_app_and_Login_into_it() {
		loginPage.loginToAmazon();
	}
	
}
