package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.interactions.PageInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	/**
	 * ThreadLocal to hold WebDriver instances for thread safety
	 */
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * Variable to store the sub-folder name for screenshots
	 */
	public static String subFolderName;

	/**
	 * Properties object to load configuration properties
	 */
	public static Properties prop;

	/**
	 * Getter for the WebDriver instance
	 *
	 * @return WebDriver instance from ThreadLocal
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * Setter for the WebDriver instance
	 *
	 * @param driverInstance WebDriver instance to be set in ThreadLocal
	 */
	private static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}

	/**
	 * Remove the WebDriver instance from the ThreadLocal
	 */
	private static void removeDriver() {
		driver.remove();
	}

	/**
	 * Load configuration properties from the config file
	 *
	 * @throws Exception if the config file cannot be loaded
	 */
	void loadConfig() throws Exception {
		prop = new Properties();
		File configFile = new File("./src/test/resources/Configuration/config.properties");
		FileInputStream fis = new FileInputStream(configFile);
		prop.load(fis);
	}

	/**
	 * Method to start the WebDriver before each test method
	 *
	 * @param browserName Optional browser name parameter, defaults to "edge"
	 * @throws Exception if the config file cannot be loaded
	 */
	@Parameters({ "browserName" })
	@BeforeMethod(alwaysRun = true)
	public void startDriver(@Optional("edge") String browserName) throws Exception {
		loadConfig();

		WebDriver driverInstance;
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driverInstance = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driverInstance = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driverInstance = new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driverInstance = new ChromeDriver();
		}
		setDriver(driverInstance);

		// Create a PageInteraction object to interact with the web page
		PageInteraction interaction = new PageInteraction(getDriver());

		// Set implicit wait and maximize the browser window
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		// Navigate to the base URL
		getDriver().get(prop.getProperty("baseUrl"));
		// Compare the current URL with the base URL
		interaction.compareText(getDriver().getCurrentUrl(), prop.getProperty("baseUrl"));
	}

	/**
	 * Method to quit the WebDriver after each test method
	 */
	@AfterMethod(alwaysRun = true)
	public void quitDriver() {
		if (getDriver() != null) {
			getDriver().quit();
			removeDriver();
		}
	}

	/**
	 * Method to capture a screenshot
	 *
	 * @param fileName Name of the screenshot file
	 */
	public void CaptureScreenShot(String fileName) {
		// Generate a sub-folder name if it is not already set
		if (subFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			subFolderName = myDateObj.format(myFormatObj);
		}

		// Take a screenshot and save it to the specified location
		TakesScreenshot screenShot = (TakesScreenshot) getDriver();
		File src = screenShot.getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/test/resources/ScreenShots/" + subFolderName + "/" + fileName + ".jpg");

		try {
			FileUtils.copyFile(src, dest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
