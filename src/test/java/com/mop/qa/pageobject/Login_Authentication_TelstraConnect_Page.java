package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class Login_Authentication_TelstraConnect_Page extends PageBase {
	public Login_Authentication_TelstraConnect_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//div[@id='tUsername']//input[@id='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath="//div[@id='tPassword']//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath="//span[@class='tc-logout']")
	private WebElement signOutBtn;
	
	@FindBy(xpath="//p[contains(text(),'You have successfully signed out')]")
	private WebElement signOutMessage;
	
	@FindBy(xpath="//div[contains(text(),'How do you rate Telstra Connect?')]")
	private WebElement rateTelstraPopUp;
	
	@FindBy(xpath="//span[contains(text(),'Not now')]")
	private WebElement notNowBtn;
	
	public void newtab(String URL) throws Exception {
		openNewTab(URL);
	}
	public void pageRefresh() throws Exception {
		commonPageRefresh();
	}
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
	public void signOut() throws Exception
	{
		clickByCSS(".tc-telstra-icon.menu.tc-icon-sm-24.undefined","Burger menu");
		click(signOutBtn,"SignOut button");
		isDisplayed(signOutMessage,"Signout Label");
	}
	public void rateTelstra() throws Exception
	{
		if(elementIsDisplayedWithoutResult(rateTelstraPopUp,"Rate Telstra popup")) {
			click(notNowBtn,"Not Now button");
		}
	}
}
