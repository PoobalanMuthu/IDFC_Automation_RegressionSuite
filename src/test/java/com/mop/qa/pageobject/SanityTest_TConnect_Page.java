package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SanityTest_TConnect_Page extends PageBase {
	public SanityTest_TConnect_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	
	@FindBy(xpath="//span[contains(text(),'Sign in to Telstra Connect ')]")
	private WebElement tConnectLabel;
	
	@FindBy(xpath = "//div[@id='tUsername']//span[contains(text(),'this?')]")
	private WebElement userNameWhatsThisLink;
	
	@FindBy(xpath = "//span[contains(text(),'Your username should be your work email address or the email address you provided when you created your Telstra Business or Enterprise account.')]")
	private WebElement userNameWhatsThisText;
	
	@FindBy(xpath = "//div[@class='t-form-field t-form-field--flex t-form-field--full-span mdc-form-field']//span[contains(text(),'this?')]")
	private WebElement rememberMeWhatsThisLink;
	
	@FindBy(xpath = "//span[contains(text(),'If you tick this box')]")
	private WebElement rememberMeWhatsThisText;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath = "//a[@id='forgotPasswordLink']")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath = "//a[contains(text(),'Privacy')]")
	private WebElement privacyTestLink;
	
	@FindBy(xpath = "//a[contains(text(),'Terms of use')]")
	private WebElement termsOfUseLink;
	
	@FindBy(xpath="//h1//a[contains(text(),'Privacy')]")
	private WebElement privacyPageLabel;
	
	@FindBy(xpath="//h1//a[contains(text(),'Terms of use')]")
	private WebElement termsOfUserPageLabel;
	
	public void enterURL(String url) throws Exception
	{
		enterUrl(url);
	}
	
	public void verifyUserNameWhatsThis() throws Exception
	{
		if(elementIsDisplayed(userNameWhatsThisLink,"What's This link")) {
			click(userNameWhatsThisLink,"What's This button");
			if(elementIsDisplayed(userNameWhatsThisText,"What's This text")) {
				System.out.println("What's This Tooltip is getting displayed");
			}
		}
	}
	public void verifyRememberMeWhatsThis() throws Exception
	{
		if(elementIsDisplayed(rememberMeWhatsThisLink,"What's This link")) {
			click(rememberMeWhatsThisLink,"What's This button");
			if(elementIsDisplayed(rememberMeWhatsThisText,"What's This text")) {
				System.out.println("What's This Tooltip is getting displayed");
			}
		}
	}
	public void verifySignInButton() throws Exception
	{
		isDisplayed(signInBtn,"Sign In button");
	}
	public void verifyResetPasswordLink() throws Exception
	{
		isDisplayed(forgotPasswordLink,"Forgot Password Link");
	}
	public void privacyTestLink() throws Exception
	{
		click(tConnectLabel,"T-Connect label");
		/*JavascriptExecutor js = (JavascriptExecutor) remoteDriver;
		js.executeScript("arguments[0].scrollIntoView();", privacyTestLink);*/
		scrollTo(privacyTestLink,"Privacy Test link");
		//scrollPage("Up",1);
		String expectedURL="https://www.telstra.com.au/privacy/privacy-statement";
		String actualURL="";
		if(elementIsDisplayed(privacyTestLink,"Privacy Test link")) {
			click(privacyTestLink,"Privacy Test Link");
			actualURL=getCurrentUrl();
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				Thread.sleep(3000);
				isDisplayed(privacyPageLabel,"Privacy Page");
				System.out.println("Navigated to current URL");
			}
			swtichTab(0);
		}
	}
	public void termsOfUserLink() throws Exception
	{
		scrollTo(termsOfUseLink,"Privacy Test link");
		String expectedURL="https://www.telstra.com.au/terms-of-use";
		String actualURL="";
		if(elementIsDisplayed(termsOfUseLink,"Terms of Use link")) {
			click(termsOfUseLink,"Terms of Use Link");
			actualURL=getCurrentUrl();
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				Thread.sleep(3000);
				isDisplayed(termsOfUserPageLabel,"Terms of Use Page");
				System.out.println("Navigated to current URL");
			}
			swtichTab(0);
		}
	}
	public void verifyClientID() throws Exception
	{
		String url = getCurrentUrl();
		String clientID = "client_id=tconnect";
		System.out.println(url);
		if ( url.toLowerCase().indexOf(clientID.toLowerCase()) != -1 ) {
			   System.out.println("Keyword Found");
			} else {
			   System.out.println("not found");
			}
	}
}
