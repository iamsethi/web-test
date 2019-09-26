package com.hellofresh.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.CLASS_NAME, using = "page-heading")
	public WebElement lbl_heading;

	@FindBy(how = How.CLASS_NAME, using = "account")
	public WebElement lbl_account;

	@FindBy(how = How.CLASS_NAME, using = "info-account")
	public WebElement lbl_info_account;

	@FindBy(how = How.CLASS_NAME, using = "logout")
	public WebElement btn_logout;

	@FindBy(how = How.LINK_TEXT, using = "Women")
	public WebElement btn_women;

	public WomenStorePage click_women_button() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(btn_women)).click();
		return new WomenStorePage(driver);
	}

}
