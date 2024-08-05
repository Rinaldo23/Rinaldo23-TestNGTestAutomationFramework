package com.qa.interactions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import com.qa.base.BasePage;

/**
 * Utility class for interacting with web elements on a page.
 */
public class PageInteraction extends BasePage {

	WebDriverWait wait;

	/**
	 * Constructor to initialize the WebDriver and WebDriverWait
	 *
	 * @param driver WebDriver instance to be used by the page object.
	 */
	public PageInteraction(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	/**
	 * Checks if the given WebElement is interactable (enabled and displayed).
	 *
	 * @param element The WebElement to check
	 * @return true if the element is interactable, false otherwise
	 */
	public boolean isElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isEnabled() && element.isDisplayed();
	}

	/**
	 * Inputs text into a WebElement after verifying it is interactable.
	 *
	 * @param element The WebElement to input text into
	 * @param value   The text value to input
	 * @throws IllegalArgumentException if the element is null or not interactable
	 */
	public void inputText(WebElement element, String value, @Optional CharSequence... keysToSend) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			if (element != null && isElementVisible(element)) {
				element.clear(); // Clear any existing text
				element.sendKeys(value); // Input the new text
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Clicks on a WebElement after verifying it is interactable.
	 *
	 * @param element The WebElement to click
	 * @throws IllegalArgumentException if the element is null or not interactable
	 */
	public void clickElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			if (element != null && isElementVisible(element)) {
				element.click();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Selects a dropdown option by visible text after verifying the dropdown and
	 * text are interactable.
	 *
	 * @param element The dropdown WebElement to select from
	 * @param text    The visible text of the option to select
	 * @throws IllegalArgumentException if the element or text is null, or if the
	 *                                  element is not interactable
	 */
	public void selectDropdownOptionByVisibleText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			if (element != null && text != null && isElementVisible(element)) {
				Select options = new Select(element);
				options.selectByVisibleText(text);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Checks if an element exists and is interactable.
	 *
	 * @param element The WebElement to check for existence
	 * @return true if the element exists and is interactable, false otherwise
	 * @throws IllegalArgumentException if the element is null or not interactable
	 */
	public boolean checkIfElementExists(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			return element != null && isElementVisible(element);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Selects a checkbox WebElement if it is not already selected.
	 *
	 * @param element The checkbox WebElement to select
	 * @throws IllegalArgumentException if the element is null or not interactable
	 */
	public void selectCheckBox(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			if (element != null && isElementVisible(element)) {
				boolean privacyPolicyFlag = element.isSelected();
				if (!privacyPolicyFlag)
					element.click();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Verifies that the current page URL contains the specified path.
	 *
	 * @param path The URL path to verify
	 * @throws IllegalArgumentException if the URL does not contain the path
	 */
	public void verifyPageNavigation(String path) {
		try {
			if (!path.isEmpty()) {
				wait.until(ExpectedConditions.urlContains(path));
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl.contains(path), true);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Sets an implicit wait time for WebDriver.
	 *
	 * @param time The time to wait in seconds
	 * @throws IllegalArgumentException if an error occurs while setting the wait
	 *                                  time
	 */
	@BeforeMethod
	public void implicitWait(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Waits explicitly for a WebElement to be visible.
	 *
	 * @param element The WebElement to wait for
	 * @param time    The time to wait in seconds
	 * @throws IllegalArgumentException if an error occurs while waiting
	 */
	public void explicitWait(WebElement element, int time) {
		try {
			if (element != null) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
				wait.until(ExpectedConditions.visibilityOf(element));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Gets the text of a WebElement after verifying it is visible.
	 *
	 * @param element The WebElement to get text from
	 * @return The text of the WebElement
	 * @throws IllegalArgumentException if an error occurs while getting the text
	 */
	public String getElementText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String text = null;
		try {
			if (element != null) {
				text = element.getText();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return text;
	}

	/**
	 * Verifies that the error text message of a WebElement matches the expected
	 * message.
	 *
	 * @param element     The WebElement containing the error message
	 * @param expectedMsg The expected error message
	 * @throws IllegalArgumentException if the actual message does not match the
	 *                                  expected message
	 */
	public void verifyErrorTextMessage(WebElement element, String expectedMsg) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			String actualMsg = getElementText(element);
			Assert.assertEquals(actualMsg, expectedMsg);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Compares the actual text with the expected text.
	 *
	 * @param actualText   The actual text
	 * @param expectedText The expected text
	 * @throws IllegalArgumentException if the actual text does not match the
	 *                                  expected text
	 */
	public void compareText(String actualText, String expectedText) {
		try {
			if (!actualText.isEmpty() && !expectedText.isEmpty())
				Assert.assertEquals(actualText, expectedText);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Gets the title of the current page.
	 *
	 * @return The page title
	 * @throws IllegalArgumentException if an error occurs while getting the title
	 */
	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return title;
	}
}
