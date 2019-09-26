package com.hellofresh.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.CLASS_NAME, using = "log")
	public WebElement btn_login;

	public void clickOnLoginButton() {
		btn_login.click();
	}

}
