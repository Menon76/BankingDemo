package com.bank.test;


import com.qa.base.BaseClass;
import com.qa.dataproviders.DataProviders;
import com.qa.pages.AccountCreationPage;
import com.qa.pages.HomePage;
import com.qa.pages.IndexPage;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AccountCreationPageTest extends BaseClass {
	private IndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage acountCreationPage;
	private HomePage homePage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		equals(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		driver.quit();
	}

	@Test(groups = "Sanity", dataProvider = "email", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest(String email) throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		acountCreationPage = loginPage.createNewAccount(email);
		boolean result = acountCreationPage.validateAcountCreatePage();
		Assert.assertTrue(result);
	}

	@Test(groups = "Regression", dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
	public void createAccountTest(HashMap<String, String> hashMapValue) throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		acountCreationPage = loginPage.createNewAccount(hashMapValue.get("Email"));
		acountCreationPage.createAccount(
				hashMapValue.get("Gender"),
				hashMapValue.get("FirstName"),
				hashMapValue.get("LastName"),
				hashMapValue.get("SetPassword"),
				hashMapValue.get("Day"),
				hashMapValue.get("Month"),
				hashMapValue.get("Year"),
				hashMapValue.get("Company"),
				hashMapValue.get("Address"),
				hashMapValue.get("City"),
				hashMapValue.get("State"),
				hashMapValue.get("Zipcode"),
				hashMapValue.get("Country"),
				hashMapValue.get("MobilePhone"));
		homePage = acountCreationPage.validateRegistration();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
	}

}
