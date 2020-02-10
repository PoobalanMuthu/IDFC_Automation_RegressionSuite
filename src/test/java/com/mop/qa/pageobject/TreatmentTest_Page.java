package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class TreatmentTest_Page extends PageBase{
	public TreatmentTest_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Something went wrong')]")
	private WebElement errorTitle;
	
	@FindBy(xpath = "//div[@class='t-error-box']")
	private WebElement errorBox;
	
	@FindBy(xpath = "//span[contains(text(),'Not Found')]")
	private WebElement errorTitle1;
	
	@FindBy(xpath="//span[contains(text(),'Sign in to Telstra')]")
	private WebElement signInLabel;
	
	@FindBy(xpath="//div[@id='tUsername']//input[@id='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath="//div[@id='tPassword']//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath="//span[contains(text(),'Please change your password')]")
	private WebElement changePasswordLabel;
	@FindBy(xpath="//div[@id='tCurrentPassword']//input[@id='password']")
	private WebElement currentPasswordTxtBox;
	@FindBy(xpath="//div[@id='tNewPassword']//input[@id='newPassword1']")
	private WebElement newPasswordTxtBox;
	@FindBy(xpath="//div[@id='tConfirmNewPassword']//input[@id='newPassword2']")
	private WebElement confirmPasswordTxtBox;
	@FindBy(xpath="//button[contains(text(),'Change password')]")
	private WebElement changePasswordBtn;
	
	@FindBy(xpath="//a[@id='forgotPasswordLink']")
	private WebElement resetPassword;
	@FindBy(xpath="//span[contains(text(),'Reset your password')]")
	private WebElement resetPasswordTitle;
	@FindBy(xpath="//button[contains(text(),'Send email')]")
	private WebElement sendEmailBtn;
	
	
	public void enterURL(String url) throws Exception
	{
		enterUrl(url);
	}
	
	public void Error1() throws Exception
	{
		isDisplayed(errorTitle,"Error Title");
		isDisplayed(errorBox,"Error description");
	}
	
	public void Error2() throws Exception
	{
		isDisplayed(errorTitle1,"Error Title");
	}
	
	public void ActiveFTL() throws Exception
	{
		isDisplayed(signInLabel,"Sign In label");
		isDisplayed(userNameTextBox,"UserName textbox");
		isDisplayed(passwordTextBox,"Password textbox");
		isDisplayed(signInBtn,"SignIn button");
	}
	
	public void changePassword() throws Exception
	{
		//scrollTo(privacyTestLink,"Privacy Test link");
		//isDisplayed(changePasswordLabel,"Change Password Label");
		isDisplayed(currentPasswordTxtBox,"Current Password Textbox");
		isDisplayed(newPasswordTxtBox,"New Password Textbox");
		isDisplayed(confirmPasswordTxtBox,"Confirm Password Textbox");
		isDisplayed(changePasswordBtn,"Change Password button");
	}
	
	public void resetPasswordPhase2() throws Exception
	{
		click(resetPassword,"Reset Password link");
		isDisplayed(resetPasswordTitle,"Reset Password Title");
		isDisplayed(sendEmailBtn,"Send Email Button");
	}
	
	public void resetPasswordLink() throws Exception
	{
		
	}
	
}
