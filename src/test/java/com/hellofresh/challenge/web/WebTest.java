package com.hellofresh.challenge.web;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.hellofresh.challenge.extent.reports.ExtentTestManager;
import com.hellofresh.challenge.pages.LoginPage;
import com.hellofresh.challenge.pages.MyAccountPage;
import com.hellofresh.challenge.pages.OrderPage;

public class WebTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

	@Test()
	public void signInTest() {
		ExtentTestManager.startTest("Create Account Test", "User should be able to create account successfully"); // Description
		LoginPage loginPage = new LoginPage(getDriver());
		MyAccountPage myAccountPage = loginPage.createEmailAndSubmit();
		data.fillCustomFields(loginPage, "NewUser");
		data.verifyCustomFields(myAccountPage, "NewAccount");
		assertTrue(getDriver().getCurrentUrl().contains("controller=my-account"));
		log.info("Account created successfully");
	}

	@Test()
	public void logInTest() {
		ExtentTestManager.startTest("Valid Login Test",
				"User should be able to login with correct username and password.");
		LoginPage loginPage = new LoginPage(getDriver());
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		data.fillCustomFields(loginPage, "ExistingUser");
		data.verifyCustomFields(myAccountPage, "ExistingAccount");
		assertTrue(getDriver().getCurrentUrl().contains("controller=my-account"));
		log.info("Valid user is able to login successfully");

	}

	@Test
	public void checkoutTest() {
		ExtentTestManager.startTest("Checkout Test", "User should be able to checkout.");
		LoginPage loginPage = new LoginPage(getDriver());
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		data.fillCustomFields(loginPage, "ExistingUser");
		OrderPage orderPage = myAccountPage.click_women_button().clickFadedShort();
		data.fillCustomFields(orderPage, "NewOrder");
		data.verifyCustomFields(orderPage, "VerifyOrder");
		assertTrue(getDriver().getCurrentUrl().contains("controller=order-confirmation"));
		log.info("User is able to checkout successfully");
	}

}
