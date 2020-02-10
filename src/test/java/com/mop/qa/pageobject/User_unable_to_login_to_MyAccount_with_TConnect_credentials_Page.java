package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class User_unable_to_login_to_MyAccount_with_TConnect_credentials_Page extends PageBase{
	public User_unable_to_login_to_MyAccount_with_TConnect_credentials_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//span[contains(text(),'The username or password you entered is incorrect.')]")
	private WebElement loginErrorMessage;
	
	public void verifyLoginErrorMessage() throws Exception {
		isDisplayed(loginErrorMessage,"Username or password invalid error message");
	}
}
