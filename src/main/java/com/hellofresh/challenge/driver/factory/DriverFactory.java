package com.hellofresh.challenge.driver.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

	WebDriver driver;
	private static String driverFile =  "src/test/resources/drivers";
	
	private static final BiFunction<String, String, WebDriver> chromeSupplier = (os, url) -> {
		switch (os) {
		case "Linux":
			System.setProperty("webdriver.chrome.driver", driverFile+"/chromedriver");
		default:
			System.setProperty("webdriver.chrome.driver", driverFile+"/chromedriver.exe");

		}
		WebDriver driver = new ChromeDriver();
		openBrowser(driver, url);
		return driver;
	};

	private static final BiFunction<String, String, WebDriver> firefoxSupplier = (os, url) -> {
		switch (os) {
		case "Linux":
			System.setProperty("webdriver.gecko.driver", driverFile+"/geckodriver");
		default:
			System.setProperty("webdriver.gecko.driver", driverFile+"/geckodriver.exe");

		}
		WebDriver driver = new FirefoxDriver();
		openBrowser(driver, url);
		return driver;
	};

	private static final BiFunction<String, String, WebDriver> internetexplorerSupplier = (os, url) -> {
		System.setProperty("webdriver.ie.driver", driverFile+"/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		openBrowser(driver, url);
		return driver;
	};

	private static final BiFunction<String, String, WebDriver> edgeSupplier = (os, url) -> {
		System.setProperty("webdriver.edge.driver", driverFile+"/MicrosoftWebDriver.exe");
		WebDriver driver = new EdgeDriver();
		openBrowser(driver, url);
		return driver;
	};

	public static void openBrowser(WebDriver driver, String url)

	{
		driver.manage().window().maximize();
		if (System.getProperty("url") != null) {
			driver.get(System.getProperty("url"));
		} else {
			driver.get(url);
		}

	}

	private static final Map<String, BiFunction<String, String, WebDriver>> MAP = new HashMap<>();

	static {
		MAP.put("chrome", chromeSupplier);
		MAP.put("firefox", firefoxSupplier);
		MAP.put("ie", internetexplorerSupplier);
		MAP.put("edge", edgeSupplier);
	}

	public static WebDriver getDriver(String browser, String os, String url) {
		if (System.getProperty("browser") != null) {
			return MAP.get(System.getProperty("browser")).apply(os, url);
		} else {
			return MAP.get(browser).apply(os, url);
		}

	}

}
