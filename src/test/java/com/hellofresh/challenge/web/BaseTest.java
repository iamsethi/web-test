package com.hellofresh.challenge.web;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hellofresh.challenge.dataProvider.JsonDataReader;
import com.hellofresh.challenge.driver.factory.DriverFactory;

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
	public void setDriver(@Optional("chrome") String browser, @Optional("windows") String os,
			@Optional("http://automationpractice.com/index.php") String url) {
		// Set Browser to ThreadLocalMap
		driver.set(DriverFactory.getDriver(browser, os, url));
		wait = new WebDriverWait(getDriver(), 10, 50);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
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