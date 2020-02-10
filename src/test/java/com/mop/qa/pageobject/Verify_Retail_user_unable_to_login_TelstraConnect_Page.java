package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class Verify_Retail_user_unable_to_login_TelstraConnect_Page extends PageBase {
	public Verify_Retail_user_unable_to_login_TelstraConnect_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	
	@FindBy(xpath="//div[@id='tUsername']//input[@id='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath="//div[@id='tPassword']//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath="//div[contains(text(),'No account')]")
	private WebElement noAccountPopUp;
	
	@FindBy(xpath="//button[contains(text(),'Close application')]")
	private WebElement closeApplicationBtn;
	
	@FindBy(xpath="//p[contains(text(),'You have successfully signed out')]")
	private WebElement signOutText;
	
	public void enterURL(String url) throws Exception
	{
		enterUrl(url);
	}
	public void enterUserName(String str,String elementName) throws Exception
	{
		enterText(userNameTextBox,str,elementName);
	}
	public void enterPassword(String str,String elementName) throws Exception
	{
		enterText(passwordTextBox,str,elementName);
	}
	public void signInclick() throws Exception
	{
		click(signInBtn,"Sign In button");
	}
	public void noAccount() throws Exception
	{
		if(elementIsDisplayed(noAccountPopUp,"No Account Popup")) {
			click(closeApplicationBtn,"Close Application button");
			isDisplayed(signOutText,"Signout Label");
		}
	}

}
