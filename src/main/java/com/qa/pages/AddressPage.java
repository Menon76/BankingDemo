package com.qa.pages;


import com.qa.Actiodriver.Action;
import com.qa.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddressPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(xpath="//span[text()='Proceed to checkout']")
	private WebElement proceedToCheckOut;
	
	public AddressPage() {
		PageFactory.initElements(driver, this);
	}

	public ShippingPage clickOnCheckOut() throws Throwable {
		action.click(driver, proceedToCheckOut);
		return new ShippingPage();
	}
	
}
