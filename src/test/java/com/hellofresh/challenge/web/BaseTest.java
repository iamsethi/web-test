package com.hellofresh.challenge.web;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hellofresh.challenge.dataProvider.JsonDataReader;
import com.hellofresh.challenge.driver.factory.DriverFactory;
import com.hellofresh.challenge.pages.HomePage;

public class BaseTest {
	protected WebDriverWait wait;
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected JsonDataReader data;

	@BeforeSuite
	public void setData() {
		JsonDataReader.initializeJSON();
		data = new JsonDataReader();

	}

	@BeforeMethod
	@Parameters(value = { "browser", "os", "url" })
	public void setDriver(@Optional("chrome") String browser, @Optional("windows") String os, String url) {
		// Set Browser to ThreadLocalMap
		driver.set(DriverFactory.getDriver(browser, os, url));
		wait = new WebDriverWait(getDriver(), 10, 50);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnLoginButton(wait);

	}

	public WebDriver getDriver() {
		// Get driver from ThreadLocalMap
		return driver.get();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

	@AfterClass
	void terminate() {
		driver.remove();
	}

}