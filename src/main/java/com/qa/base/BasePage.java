package com.qa.base;

import org.openqa.selenium.WebDriver;

/**
 * Base class for page objects, providing a WebDriver instance for browser
 * interactions.
 */
public class BasePage {

	// Protected WebDriver instance to be used by subclasses for browser operations
	protected WebDriver driver;

	/**
	 * Constructor for BasePage. Initializes the WebDriver instance for browser
	 * interactions.
	 *
	 * @param driver The WebDriver instance used to interact with the web browser
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

}
