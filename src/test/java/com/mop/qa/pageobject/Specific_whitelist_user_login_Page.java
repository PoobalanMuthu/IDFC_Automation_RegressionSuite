package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class Specific_whitelist_user_login_Page extends PageBase{
	public Specific_whitelist_user_login_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInWhitelistBtn;
	
	public void clickSignIn() throws Exception {
		click(signInWhitelistBtn,"Signin Button");
	}
}
