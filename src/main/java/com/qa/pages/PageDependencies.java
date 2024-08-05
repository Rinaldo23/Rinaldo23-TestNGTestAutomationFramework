package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;

public class PageDependencies extends BasePage {

	/**
	 * Page objects for different pages in the application.
	 */
	private AccountPage accountPage;
	private HomePage homePage;
	private LoginPage loginPage;
	private RegisterPage registerPage;

	/**
	 * Constructor to initialize page objects.
	 *
	 * @param driver WebDriver instance to be used by the page objects.
	 */
	public PageDependencies(WebDriver driver) {
		super(driver);
		this.accountPage = new AccountPage(driver);
		this.homePage = new HomePage(driver);
		this.loginPage = new LoginPage(driver);
		this.registerPage = new RegisterPage(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Provides access to the AccountPage object.
	 *
	 * @return The AccountPage object
	 */
	public AccountPage accountPage() {
		return accountPage;
	}

	/**
	 * Provides access to the HomePage object.
	 *
	 * @return The HomePage object
	 */
	public HomePage homePage() {
		return homePage;
	}

	/**
	 * Provides access to the LoginPage object.
	 *
	 * @return The LoginPage object
	 */
	public LoginPage LoginPage() {
		return loginPage;
	}

	/**
	 * Provides access to the RegisterPage object.
	 *
	 * @return The RegisterPage object
	 */
	public RegisterPage RegisterPage() {
		return registerPage;
	}
}
