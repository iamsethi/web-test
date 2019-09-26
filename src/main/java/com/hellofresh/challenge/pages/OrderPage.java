package com.hellofresh.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderPage extends BasePage {

	public OrderPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(how = How.NAME, using = "Submit")
	public WebElement btn_submit;

	@FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	public WebElement btn_checkout;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	public WebElement btn_proceed_checkout;

	@FindBy(how = How.NAME, using = "processAddress")
	public WebElement btn_process_address;

	@FindBy(how = How.ID, using = "uniform-cgv")
	public WebElement rbn_accept_term;	

	@FindBy(how = How.NAME, using = "processCarrier")
	public WebElement btn_process_carrier;
	
	@FindBy(how = How.CLASS_NAME, using = "bankwire")
	public WebElement btn_pay_by_bankwire;
	
	@FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
	public WebElement btn_confirm_order;
	
	//verification
	@FindBy(how = How.CSS, using = "h1")
	public WebElement lbl_heading;
	
	@FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
	public WebElement lbl_shipping;
	
	@FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
	public WebElement lbl_payment;
	
	@FindBy(how = How.XPATH, using = "//*[@class='cheque-indent']/strong")
	public WebElement lbl_order_complete;
	


}
