package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class Login_Authentication_TWHub_Page extends PageBase {
	public Login_Authentication_TWHub_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
	private WebElement dashboardLabel;
	
	public void signOut() throws Exception
	{
		isDisplayed(dashboardLabel,"Dashboard");
	}
}
