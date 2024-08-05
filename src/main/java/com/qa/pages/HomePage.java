package com.qa.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.BasePage;
import com.qa.interactions.PageInteraction;

public class HomePage extends BasePage {

	/**
	 * Constructor to initialize the WebDriver and PageFactory elements.
	 *
	 * @param driver WebDriver instance to be used by the page object.
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * PageInteraction instance for interacting with web elements.
	 */
	PageInteraction interaction = new PageInteraction(driver);

	// Web Elements

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	private WebElement myAccountBtn;

	@FindBy(linkText = "Register")
	private WebElement registerBtn;

	@FindBy(partialLinkText = "Logi")
	private WebElement loginBtn;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='product-thumb']//div[2]/div/h4")
	private List<WebElement> productNameList;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement productNameMismatch;
	
	@FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
	private WebElement searchBtn;

	// Methods

	/**
	 * Navigates to the Register page.
	 *
	 * @return A RegisterPage instance
	 */
	public RegisterPage NavigateToRegisterPage() {
		interaction.clickElement(myAccountBtn);
		interaction.clickElement(registerBtn);
		interaction.verifyPageNavigation("account/register");
		return new RegisterPage(driver);
	}

	/**
	 * Navigates to the Login page.
	 *
	 * @return A LoginPage instance
	 */
	public LoginPage NavigateToLoginPage() {
		interaction.clickElement(myAccountBtn);
		interaction.clickElement(loginBtn);
		interaction.verifyPageNavigation("account/login");
		return new LoginPage(driver);
	}

	/**
	 * Enters the product name into the search box and triggers a search.
	 *
	 * @param productName The name of the product to search for
	 * @return The current HomePage instance
	 */
	public HomePage EnterProductName(String productName) {
		interaction.inputText(searchBox, productName, Keys.ENTER);
		interaction.clickElement(searchBtn);
		interaction.explicitWait(productNameList.get(0), 10);
		return this;
	}

	/**
	 * Searches for a product by name and returns the WebElement if found.
	 *
	 * @param productName The name of the product to search for
	 * @return The WebElement representing the found product, or null if not found
	 */
	public WebElement SearchProduct(String productName) {
		WebElement ele = null;

		interaction.implicitWait(10);
		EnterProductName(productName);

		if (productNameList.size() != 0) {
			interaction.explicitWait(productNameList.get(productNameList.size() - 1), 10);
		} else {
			return ele;
		}

		for (WebElement element : productNameList) {
			String name = element.getText();
			if (name.toLowerCase().contains(productName.toLowerCase())) {
				ele = element;
				break;
			}
		}

		return ele;
	}

	/**
	 * Checks if a product exists by name and verifies the search results.
	 *
	 * @param productName The name of the product to check
	 */
	public void checkIfProductExists(String productName) {
		interaction.implicitWait(10);
		WebElement element = SearchProduct(productName);

		if (element == null) {
			interaction.explicitWait(productNameMismatch, 10);

			if (interaction.isElementVisible(productNameMismatch)) {
				interaction.compareText(productNameMismatch.getText(),
						"There is no product that matches the search criteria.");
			}
		} else {
			Assert.fail("The specified product exists...!!");
		}
	}
}
