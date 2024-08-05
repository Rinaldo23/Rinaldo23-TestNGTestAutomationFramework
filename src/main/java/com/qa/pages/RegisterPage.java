package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.BasePage;
import com.qa.interactions.PageInteraction;
import com.qa.utility.DataGenerator;

public class RegisterPage extends BasePage {

	/**
	 * Constructor to initialize the WebDriver and PageFactory elements.
	 *
	 * @param driver WebDriver instance to be used by the page object.
	 */
	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/* PageInteraction instance for interacting with web elements. */
	PageInteraction interaction = new PageInteraction(driver);

	/* DataGenerator instance for generating random data. */
	DataGenerator data = new DataGenerator();

	// Web Elements

	@FindBy(name = "firstname")
	private WebElement firstNameTextBox;

	@FindBy(xpath = "//div[@id='error-firstname']")
	private WebElement errorFirstNameTextMsg;

	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;

	@FindBy(css = "#error-lastname")
	private WebElement errorLastNameTextMsg;

	@FindBy(xpath = "//input[@placeholder='E-Mail']")
	private WebElement emailTextBox;

	@FindBy(css = "#error-email")
	private WebElement errorEmailTextMsg;

	@FindBy(css = "#input-password")
	private WebElement passwordTextBox;

	@FindBy(css = "#error-password")
	private WebElement errorPasswordTextMsg;

	@FindBy(xpath = "//input[@id='input-newsletter']")
	private WebElement newsLetterCheckBox;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicyCheckBox;

	@FindBy(xpath = "//dirv[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyAlert;

	@FindBy(css = "button[type='submit']")
	private WebElement continueBtn;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	private WebElement accountCreationMsg;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	private WebElement accountCreationContinueBtn;

	private String[] errorMessages = { "First Name must be between 1 and 32 characters!",
			"Last Name must be between 1 and 32 characters!", "E-Mail Address does not appear to be valid!",
			"Password must be between 4 and 20 characters!" };

	// Methods

	/**
	 * Enters user details into the registration form.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage EnterUserDetails() {
		interaction.inputText(firstNameTextBox, "Test");
		interaction.inputText(lastNameTextBox, "User");
		interaction.inputText(emailTextBox, data.generateRandomEmail());
		interaction.inputText(passwordTextBox, "Test@12345");
		return this;
	}

	/**
	 * Placeholder method for entering blank user details.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage EnterBlankUserDetails() {
		return this;
	}

	/**
	 * Selects the privacy policy checkbox.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage SelectPrivacyCheckBox() {
		interaction.selectCheckBox(privacyPolicyCheckBox);
		return this;
	}

	/**
	 * Selects the newsletter checkbox.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage SelectNewsLetterCheckBox() {
		interaction.selectCheckBox(newsLetterCheckBox);
		return this;
	}

	/**
	 * Clicks the continue button to submit the registration form.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage Continue() {
		interaction.clickElement(continueBtn);
		return this;
	}

	/**
	 * Checks for the account creation message and navigates to the account page.
	 *
	 * @return An AccountPage instance
	 */
	public AccountPage CheckAccountCreatedMessage() {
		interaction.explicitWait(accountCreationMsg, 10);
		interaction.verifyPageNavigation("account/success");
		interaction.clickElement(accountCreationContinueBtn);
		interaction.verifyPageNavigation("account/account");
		return new AccountPage(driver);
	}

	/**
	 * Verifies the presence of the privacy policy alert message.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage PrivacyPolicyAlert() {
		boolean val = interaction.isElementVisible(privacyPolicyAlert);
		Assert.assertEquals(val, true);
		return this;
	}

	/**
	 * Verifies the error text messages for form validation.
	 */
	public void verifyErrorTextMessages() {
		interaction.verifyErrorTextMessage(errorFirstNameTextMsg, errorMessages[0]);
		interaction.verifyErrorTextMessage(errorLastNameTextMsg, errorMessages[1]);
		interaction.verifyErrorTextMessage(errorEmailTextMsg, errorMessages[2]);
		interaction.verifyErrorTextMessage(errorPasswordTextMsg, errorMessages[3]);
	}

	/**
	 * Verifies the page title.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage verifyPageTitle() {
		interaction.compareText("Register Account", interaction.getPageTitle());
		return this;
	}

	/**
	 * Verifies the page URL.
	 *
	 * @return The current RegisterPage instance
	 */
	public RegisterPage verifyPageUrl() {
		interaction.verifyPageNavigation("account/register");
		return this;
	}
}
