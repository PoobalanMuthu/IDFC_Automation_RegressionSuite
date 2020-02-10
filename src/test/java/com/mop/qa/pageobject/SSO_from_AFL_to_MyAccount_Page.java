package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SSO_from_AFL_to_MyAccount_Page extends PageBase{
	public SSO_from_AFL_to_MyAccount_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//div[@class='layout-cell layout-cell--other']//span[contains(text(),'Login')]")
	private WebElement loginDropDown;

	@FindBy(xpath="//a[contains(text(),'Log In')]")
	private WebElement loginButton;
	@FindBy(id="username")
	private WebElement userNameTextbox;
	@FindBy(id="password")
	private WebElement passwordTextbox;
	@FindBy(id="login-btn")
	private WebElement loginButtonLoginPage;
	@FindBy(xpath="//span[contains(text(),'Hi')]")
	private WebElement loginVerification;
	@FindBy(xpath="//h2[contains(text(),'Sorry')]")
	private WebElement myAccountErrorMessage;
	@FindBy(xpath="//p[@class='t-error-box']")
	private WebElement testApplicationErrorMessage;
	//FN1
	@FindBy(xpath="//h1[contains(text(),'Forbidden')]")
	private WebElement forbidden;

	public void enterURL(String url) throws Exception
	{
		enterUrl(url);
	}
	public void login(String username, String password, String environment) throws Exception
	{
		/*if(environment.equalsIgnoreCase("PROD")) {
			//PROD only
			click(loginDropDown,"Login dropdown");
			click(loginButton,"Login button");
		}*/
		enterText(userNameTextbox,username,"Username textbox");
		enterText(passwordTextbox,password,"Password textbox");
		click(loginButtonLoginPage,"Login button");
		Thread.sleep(5000);
	}
	public void myAccount(String environment, String URL) throws Exception {
		if(environment.equalsIgnoreCase("PROD")) {
			//PROD
			if(elementIsDisplayed(loginVerification,"Login successful")) {
				openNewTab(URL);
				isDisplayed(myAccountErrorMessage,"Error message in My Account page");
			}
		} else if(environment.equalsIgnoreCase("FN1")) {
			//FN1
			if(elementIsDisplayed(forbidden,"Login successful")) {
				openNewTab(URL);
				isDisplayed(myAccountErrorMessage,"Error message in My Account page");
			}
		}
	}
	public void testApplication(String environment, String URL) throws Exception {
		if(environment.equalsIgnoreCase("PROD")) {
			enterUrl(URL);
		}
		enterUrl(URL);
		isDisplayed(testApplicationErrorMessage,"Error message in Test Application");
	}
}
