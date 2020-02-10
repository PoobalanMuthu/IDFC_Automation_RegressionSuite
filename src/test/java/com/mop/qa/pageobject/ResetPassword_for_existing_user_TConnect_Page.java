package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class ResetPassword_for_existing_user_TConnect_Page extends PageBase{

	public ResetPassword_for_existing_user_TConnect_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath = "//a[@id='forgotPasswordLink']")
	private WebElement forgotPasswordLink;

	@FindBy(xpath="//button[contains(text(),'Send email')]")
	private WebElement sendEmailBtn;

	@FindBy(xpath="//span[contains(text(),'Check your email')]")
	private WebElement checkEmailText;

	@FindBy(xpath="//div[@id='tNewPassword']//input[@id='newPassword1']")
	private WebElement newPasswordTextBox;

	@FindBy(xpath="//div[@id='tConfirmNewPassword']//input[@id='newPassword2']")
	private WebElement reEnterNewPasswordTextBox;

	@FindBy(xpath="//button[contains(text(),'Reset password')]")
	private WebElement resetPasswordButton;

	@FindBy(xpath="//a[contains(text(),'Reset my password')]")
	private WebElement resetMyPasswordButton; 

	@FindBy(xpath="//p[@role='alert']")
	private WebElement loginPageAlert;

	//Mailinator Elements
	@FindBy(xpath="//div[@class='hidden-sm hidden-xs']//input[@type='text']")
	private WebElement userNameTextBox;

	@FindBy(xpath="//div[@class='hidden-sm hidden-xs']//button[contains(text(),'Go!')]")
	private WebElement goButton;

	@FindBy(xpath="//td[contains(text(),'Forgotten your Telstra password?')]")
	private WebElement forgottenTelstraPasswordEmail;

	@FindBy(xpath="//td[contains(text(),'Reset your password')]")
	private WebElement resetYourPasswordEmail;

	@FindBy(xpath="//p[contains(text(),'Or copy and paste')]//a[@rel=\\\"nofollow\\\"]")
	private WebElement magicLink;

	@FindBy(xpath="//td[@class='button-purple']")
	private WebElement mailinatorResetPasswordBtn;

	public void forgotPasswordFlow(String mailinatorUserName,String environment) throws Exception
	{
		String mailinatorURL="https://www.mailinator.com/";
		scrollTo(forgotPasswordLink,"Forgot Password Link");
		if(elementIsDisplayed(forgotPasswordLink,"Forgot Password Link")) {
			click(forgotPasswordLink,"Forgot Password Link");
			Thread.sleep(90000);
			click(sendEmailBtn,"Send Email button");
			if(elementIsDisplayed(checkEmailText,"Check Email text")) {
				openNewTab(mailinatorURL);
				if(environment.equalsIgnoreCase("FN1")) {
					enableProxy();
				}
				enterText(userNameTextBox,mailinatorUserName,"UserName");
				Thread.sleep(7000);
				click(goButton,"Go button");
				if(elementIsDisplayed(forgottenTelstraPasswordEmail,"Forgotten Telstra Password Email"))
				{
					click(forgottenTelstraPasswordEmail,"Forgotten Telstra Password Email");
					//String magicLinkURL=getText(magicLink,"Magic Link");
					//String magicLinkURL=getTextNew("//p[contains(text(),'Or copy and paste')]//a[@rel='nofollow']");
					//String magicLinkURL = getAttributeValue(magicLink,"href");
					//System.out.println(magicLinkURL);
					//openNewTab(magicLinkURL);
					switchToFrame("msg_body");
					click(mailinatorResetPasswordBtn,"Reset My Password button");
					swtichTab(2);
				} else if(elementIsDisplayed(resetYourPasswordEmail,"Reset Telstra Password Email")) {
					click(resetYourPasswordEmail,"Reset Telstra Password Email");
					switchToFrame("msg_body");
					click(mailinatorResetPasswordBtn,"Reset My Password button");
					swtichTab(2);
				}
				if(environment.equalsIgnoreCase("PROD")) {
					//Local
					//disable();
					//RDP
					enableWPad();
				} else if(environment.equalsIgnoreCase("FN1")) {
					enableWPad();
				}
				Thread.sleep(5000);
				enterText(newPasswordTextBox,"Pass12345","New password");
				enterText(reEnterNewPasswordTextBox,"Pass12345","Re enter new password");
				click(resetPasswordButton,"Reset password button");
				if(elementIsDisplayedWithoutResult(loginPageAlert,"Alert is displayed")) {
					enterText(newPasswordTextBox,"Change1234","New password");
					enterText(reEnterNewPasswordTextBox,"Change1234","Re enter new password");
					click(resetPasswordButton,"Reset password button");
				}
				else if(elementIsDisplayedWithoutResult(loginPageAlert,"Alert is displayed")) {
					enterText(newPasswordTextBox,"Pass123456","New password");
					enterText(reEnterNewPasswordTextBox,"Pass123456","Re enter new password");
					click(resetPasswordButton,"Reset password button");
				}
				else if(elementIsDisplayedWithoutResult(loginPageAlert,"Alert is displayed")) {
					enterText(newPasswordTextBox,"Pass1234567","New password");
					enterText(reEnterNewPasswordTextBox,"Pass1234567","Re enter new password");
					click(resetPasswordButton,"Reset password button");
				}

			}
		}
	}

	public void passwordResetEmail() throws Exception{
		swtichTab(1);
	}
}
