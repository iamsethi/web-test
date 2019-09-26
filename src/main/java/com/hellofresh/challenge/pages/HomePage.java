package com.hellofresh.challenge.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.CLASS_NAME, using = "login")
	private WebElement btn_login;

	public void clickOnLoginButton(WebDriverWait wait) {
		checkIfStale(wait);
		btn_login.click();
	}

	private void checkIfStale(WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btn_login));
			btn_login.click();
		} catch (ElementClickInterceptedException | StaleElementReferenceException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", btn_login);
		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		}
	}

}
