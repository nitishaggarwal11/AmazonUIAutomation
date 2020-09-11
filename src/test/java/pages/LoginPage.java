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
import utils.Excel;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	/***
	 * Constructor - initialize driver and web elements 
	 * @param driver
	 */
	public LoginPage (WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		wait = new WebDriverWait(driver, Integer.parseInt(ApplicationProperties.getInstance().getProperty(ConfigurationProperties.EXPLICIT_WAIT_STRING)));
	}
	
	/***
	 * WebElements of 'Login' page
	 */
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement AlreadyACustomerSignInButton;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='outer-accordion-signin-signup-page']//android.view.View")
	private WebElement WelcomeTextHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='ap_email_login']")
	private WebElement MobileNumberOrEmailField;
	
	@FindBy(how = How.ID, using = "login_accordion_header")
	private WebElement LoginRadioButton;
	
	@FindBy(how = How.ID, using = "register_accordion_header")
	private WebElement CreateAccountRadioButton;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='continue']")
	private WebElement ContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='a-page']//android.view.View[2]")
	private WebElement LoginTextHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='ap_password']")
	private WebElement PasswordField;
	
	@FindBy(how = How.XPATH, using = "//*[@resource-id='signInSubmit']")
	private WebElement LoginButton;
	
	@FindBy(how = How.ID, using = "auth-fpp-link-bottom")
	private WebElement ForgotPasswordButton;
	
	@FindBy(how = How.ID, using = "auth-show-password-checkbox-container")
	private WebElement ShowPasswordCheckbox;
	
	
	/***
	 * @description Method to Login in Amazon App
	 */
	public void loginToAmazon() 
	{
		try {
			AlreadyACustomerSignInButton.click();
			
			wait.until(ExpectedConditions.visibilityOf(WelcomeTextHeader));
			Assert.assertEquals(ApplicationProperties.getInstance().getProperty(ConfigurationProperties.WELCOME_HEADER_TEXT),
					WelcomeTextHeader.getAttribute("text"));
			
			MobileNumberOrEmailField.sendKeys(Excel.getData().get("EmailAddress"));
			ContinueButton.click();
			
			wait.until(ExpectedConditions.visibilityOf(LoginTextHeader));
			Assert.assertEquals(ApplicationProperties.getInstance().getProperty(ConfigurationProperties.LOGIN_HEADER_TEXT),
					LoginTextHeader.getAttribute("text"));
			
			PasswordField.sendKeys(Excel.getData().get("Password"));
			LoginButton.click();
		} catch (Exception e) {
			Reporter.addStepLog("Exception in Logging to the application");
		}
	}

}
