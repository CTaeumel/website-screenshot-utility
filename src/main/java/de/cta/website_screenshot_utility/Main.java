package de.cta.website_screenshot_utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * This class is the entrancy point of this tool. It is designed to be a small
 * CLI-utility to take screenshots of websites. It is designed for Firefox.<br>
 * <br>
 * Arguments:<br>
 * 1: URL of the website<br>
 * 2: Width of the resulting screenshot<br>
 * 3: Height of the resulting screenshot<br>
 * 4: Name of the screenshot-file (absolute or relative)<br>
 * 5: Time to wait before taking the screenshot in milliseconds (to make sure
 * that the website is ready, optional, default: 250 milliseconds)<br>
 * <br>
 * Note: The arguments won't be validated/checked!<br>
 * 
 * @author Christian Taeumel
 *
 */
public class Main {
	private static String pathGeckodriver = "drivers/geckodriver.current";

	public static void main(String[] args) throws Exception {

		// Check if all necessary arguments have been passed to the script
		if (args.length < 4) {
			System.out.println("At least one argument is missing!");
			System.out.println("Number of arguments: " + args.length);
			for (int i = 0; i < args.length; i++) {
				System.out.println("Argument " + i + ": " + args[i]);
			}
			System.exit(1);
		}

		// Get variables
		String url = args[0];
		Integer width = Integer.parseInt(args[1]);
		Integer height = Integer.parseInt(args[2]);
		String screenshotName = args[3];
		int timeToWaitBeforeTakingTheScreenshotInMilliseconds = 250;

		if (args.length == 5) {
			timeToWaitBeforeTakingTheScreenshotInMilliseconds = Integer.parseInt(args[4]);
		}

		// Register driver
		System.setProperty("webdriver.gecko.driver", pathGeckodriver);

		// Set parameter to make sure that the browser starts in the background
		// (headless)
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("-headless");
		firefoxBinary.addCommandLineOptions("-private");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);

		// Start browser and take screenshot
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		driver.manage().window().setSize(new Dimension(width.intValue(), height.intValue()));
		driver.get(url);
		// Wait x milliseconds to make sure that the website has finished loading.
		TimeUnit.MILLISECONDS.sleep(timeToWaitBeforeTakingTheScreenshotInMilliseconds);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotName));
		driver.close();
	}
}