package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class TemporaryPassword_change_for_new_user_TelstraConnect_Page extends PageBase{
	public TemporaryPassword_change_for_new_user_TelstraConnect_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//div[@id='tUsername']//input[@id='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath="//div[@id='tPassword']//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath="//div[@id='tCurrentPassword']//input[@id='password']")
	private WebElement currentPasswordTextBox;
	
	@FindBy(xpath="//div[@id='tNewPassword']//input[@id='newPassword1']")
	private WebElement newPasswordTextBox;
	
	@FindBy(xpath="//div[@id='tConfirmNewPassword']//input[@id='newPassword2']")
	private WebElement confirmNewPasswordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Change password')]")
	private WebElement changePasswordBtn;
	
	@FindBy(xpath="//h1[contains(text(),'Congratulations! You have successfully changed your password.')]")
	private WebElement changePasswordSuccessfulBanner;
	
	@FindBy(xpath="//a[contains(text(),'Sign in to Telstra Connect')]")
	private WebElement signInBtn2;
	
	@FindBy(xpath="//span[@class='tc-logout']")
	private WebElement signOutBtn;
	
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
	
	public void verifyResetPassword(String password) throws Exception
	{
		enterText(currentPasswordTextBox,password,"Current Password");
		enterText(newPasswordTextBox,"Pass1234","Current Password");
		enterText(confirmNewPasswordTextBox,"Pass1234","Current Password");
		click(changePasswordBtn,"Change Password button");
		Thread.sleep(5000);
		if(elementIsDisplayed(changePasswordSuccessfulBanner,"Password changed successfully banner"))
		{
			click(signInBtn2,"Sign In button");
		}
	}
	
	public void signOut() throws Exception
	{
		clickByCSS(".tc-telstra-icon.menu.tc-icon-sm-24.undefined","Burger menu");
		click(signOutBtn,"SignOut button");
		isDisplayed(signOutText,"Signout Label");
	}
}
