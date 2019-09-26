package com.hellofresh.challenge.pages;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "email_create")
	public WebElement tbx_email_create;

	@FindBy(how = How.ID, using = "SubmitCreate")
	public WebElement btn_submit_create;

	@FindBy(how = How.ID, using = "email")
	public WebElement tbx_email;

	@FindBy(how = How.ID, using = "SubmitLogin")
	public WebElement btn_SubmitLogin;

	@FindBy(how = How.ID, using = "SubmitCreate")
	public WebElement btn_submit;

	@FindBy(how = How.ID, using = "id_gender2")
	public WebElement rbn_gender2;

	@FindBy(how = How.ID, using = "customer_firstname")
	public WebElement tbx_customer_firstname;

	@FindBy(how = How.ID, using = "customer_lastname")
	public WebElement tbx_customer_lastname;

	@FindBy(how = How.ID, using = "passwd")
	public WebElement tbx_password;

	@FindBy(how = How.ID, using = "days")
	public WebElement ddl_days;

	@FindBy(how = How.ID, using = "months")
	public WebElement ddl_months;

	@FindBy(how = How.ID, using = "years")
	public WebElement ddl_years;

	@FindBy(how = How.ID, using = "company")
	public WebElement tbx_company;

	@FindBy(how = How.ID, using = "address1")
	public WebElement tbx_address1;

	@FindBy(how = How.ID, using = "address2")
	public WebElement tbx_address2;

	@FindBy(how = How.ID, using = "city")
	public WebElement tbx_city;

	@FindBy(how = How.ID, using = "id_state")
	public WebElement ddl_state;

	@FindBy(how = How.ID, using = "postcode")
	public WebElement tbx_postcode;

	@FindBy(how = How.ID, using = "other")
	public WebElement tbx_other;

	@FindBy(how = How.ID, using = "phone")
	public WebElement tbx_phone;

	@FindBy(how = How.ID, using = "phone_mobile")
	public WebElement tbx_phone_mobile;

	@FindBy(how = How.ID, using = "alias")
	public WebElement tbx_alias;

	@FindBy(how = How.ID, using = "submitAccount")
	public WebElement btn_submitAccount;

	public MyAccountPage createEmailAndSubmit() {
		String timestamp = String.valueOf(new Date().getTime());
		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
		tbx_email_create.sendKeys(email);
		btn_submit_create.click();
		return new MyAccountPage(driver);
	}

}
