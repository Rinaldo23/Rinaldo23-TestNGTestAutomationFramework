package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;
import com.qa.interactions.PageInteraction;

public class AccountPage extends BasePage {

	/**
	 * Constructor to initialize the WebDriver and PageFactory elements.
	 *
	 * @param driver WebDriver instance to be used by the page object.
	 */
	public AccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * PageInteraction instance for interacting with web elements.
	 */
	PageInteraction interaction = new PageInteraction(driver);

	// Web Elements

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	private WebElement myAccountText;

	@FindBy(xpath = "//a[normalize-space()='Subscribe / unsubscribe to newsletter']")
	private WebElement subsNewsLetterOption;

	@FindBy(css = "#input-newsletter")
	private WebElement newsLetterCheckBox;

	// Methods

	/**
	 * Checks if the newsletter subscription checkbox is selected.
	 * 
	 * Verifies page navigation, clicks the newsletter subscription option, and then
	 * checks the status of the newsletter checkbox.
	 * 
	 * @return true if the newsletter checkbox is selected, false otherwise
	 */
	public boolean IsNewsLetterSubscribed() {
		interaction.implicitWait(10);
		interaction.verifyPageNavigation("account/account");
		interaction.clickElement(subsNewsLetterOption);
		interaction.verifyPageNavigation("account/newsletter");
		return newsLetterCheckBox.isSelected();
	}
}
