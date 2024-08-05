package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;
import com.qa.interactions.PageInteraction;

public class LoginPage extends BasePage {

	/**
	 * Constructor to initialize the WebDriver and PageFactory elements.
	 *
	 * @param driver WebDriver instance to be used by the page object.
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * PageInteraction instance for interacting with web elements.
	 */
	PageInteraction interaction = new PageInteraction(driver);

	// Web Elements

	@FindBy(id = "input-email")
	private WebElement emailTextBox;

	@FindBy(name = "password")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	private WebElement loginTextMsg;

	@FindBy(xpath = "//dirv[@class='alert alert-danger alert-dismissible']")
	private WebElement inValidLoginCredsAlert;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	private WebElement loginBtn;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgotPasswordLink;

	@FindBy(xpath = "//h1[normalize-space()='Forgot Your Password?']")
	private WebElement forgotPasswordText;

	// Methods

	/**
	 * Enters login details (email and password) into the respective fields.
	 *
	 * @param email    The email address for login
	 * @param password The password for login
	 * @return The current LoginPage instance
	 */
	public LoginPage EnterLoginDetails(String email, String password) {
		interaction.inputText(emailTextBox, email);
		interaction.inputText(passwordTextBox, password);
		return this;
	}

	/**
	 * Enters invalid login details into the email and password fields.
	 *
	 * @param email    The email address for login (invalid)
	 * @param password The password for login (invalid)
	 * @return The current LoginPage instance
	 */
	public LoginPage EnterInValidLoginDetails(String email, String password) {
		interaction.inputText(emailTextBox, "invalidEmail23@gmail.com");
		interaction.inputText(passwordTextBox, "invalid@12345");
		return this;
	}

	/**
	 * Clicks the login button to submit the login form.
	 *
	 * @return The current LoginPage instance
	 */
	public LoginPage ClickOnLoginButton() {
		interaction.clickElement(loginBtn);
		return this;
	}

	/**
	 * Confirms successful login by verifying the presence of login message and
	 * checking navigation to the account page.
	 *
	 * @return The current LoginPage instance
	 */
	public LoginPage ConfirmSuccessfulLogin() {
		interaction.explicitWait(loginTextMsg, 10);
		interaction.verifyPageNavigation("account/account");
		return this;
	}

	/**
	 * Confirms login failure by checking for invalid login credentials alert and
	 * verifying navigation remains on the login page.
	 *
	 * @return The current LoginPage instance
	 */
	public LoginPage ConfirmLoginFailure() {
		interaction.explicitWait(inValidLoginCredsAlert, 10);
		interaction.verifyPageNavigation("account/login");
		return this;
	}

	/**
	 * Clicks on the "Forgot Password" link to navigate to the password recovery
	 * page.
	 *
	 * @return The current LoginPage instance
	 */
	public LoginPage ClickOnForgotPasswordLink() {
		interaction.clickElement(forgotPasswordLink);
		return this;
	}

	/**
	 * Confirms that the "Forgot Your Password?" text is visible and verifies
	 * navigation to the forgotten password page.
	 *
	 * @return The current LoginPage instance
	 */
	public LoginPage ConfirmForgotPasswordLinkIsVisible() {
		interaction.isElementVisible(forgotPasswordText);
		interaction.verifyPageNavigation("account/forgotten");
		return this;
	}
}
