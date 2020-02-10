package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class TwHubPages extends PageBase {

	public TwHubPages(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//*[contains(text(),'Welcome to the Telstra Wholesale Hub')]")
	private WebElement welcomePage;

	@FindBy(xpath = "//span[contains(text(),'Sign in to Telstra Wholesale Hub')]")
	private WebElement signInMessage;

	
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(id = "forgotPasswordLink")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath = "//button[contains(text(),'Send email')]")
	private WebElement sendEmail;

	@FindBy(xpath = "//span[contains(text(),'Check your inbox')]")
	private WebElement checkYourInbox;
	
	@FindBy(partialLinkText = "Back to sign in")
	private WebElement backToSignin;
	
	@FindBy(xpath = "//span[contains(text(),'Create a new password')]")
	private WebElement verifyCreateNewPasswordPage;
	
	@FindBy(id = "newPassword1")
	private WebElement newPassword;
	
	@FindBy(id = "newPassword2")
	private WebElement reEnterNewPassword;

	@FindBy(xpath = "//button[contains(text(),'Save New Password')]")
	private WebElement saveNewPassword;
	
	public void twhubLoginPage(String url) {
		try {
			enterUrl(url);
			elementIsDisplayed(signInMessage);
		} catch (Exception e) {
		}
	}

	public void welcomePageTwHub() {
		try {
			verifyText(welcomePage, "Welcome to the Telstra Wholesale Hub");
			// elementIsDisplayed(welcomePage);
		} catch (Exception e) {

		}
	}

	public void resetPassword(String userName) {
		try {
			enterText(username, userName, "username");
			scrollTo(forgotPasswordLink, "Reset Your Password");
			click(forgotPasswordLink, "Reset Your Password");
			// In Reset Your Password Page
			switchToFrameToVerifyReCaptcha();
			switchToParentFrame();
			click(sendEmail, "Send Email");
			elementIsDisplayed(checkYourInbox);
			click(backToSignin, "Back to Sign-in");
		} catch (Exception e) {
		}
	}
	
	public void createNewPasswordPageTwHub() {
		try {
			//switchToParentFrame();
			String NewPassword = password();
			verifyText(verifyCreateNewPasswordPage, "Create a new password");
			enterText(newPassword, NewPassword, "New Password");
			enterText(reEnterNewPassword, NewPassword, "ReEnter New Password");
			click(saveNewPassword, "save new Password Button");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
