package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class TconnectPages extends PageBase {
	
	public TconnectPages(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//span[contains(text(),'Sign in to Telstra Connect')]")
	private WebElement loginPage;
	
	@FindBy(id = "forgotPasswordLink")
	private WebElement forgotPasswordLink;
	
	@FindBy(id = "username")
	private WebElement usernameId;
	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	private WebElement continueInResetPasswordPage;
	
	@FindBy(xpath = "//span[contains(text(),'Check your inbox')]")
	private WebElement checkYourInbox;
	
	
	@FindBy(xpath = "//span[contains(text(),'Reset your password')]")
	private WebElement verifyCreateNewPasswordPage;
	
	@FindBy(id = "newPassword1")
	private WebElement newPassword;

	@FindBy(id = "newPassword2")
	private WebElement reEnterNewPassword;
	
	
	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	private WebElement SignInToCreateNewPassword;
	
	
	@FindBy(xpath = "//span[contains(text(),'Telstra Connect')]")
	private WebElement dashboardPage;
	
	public void tconnectLoginPage(String url){
		try {
			enterUrl(url);
			verifyText(loginPage, "Sign in to Telstra Connect");
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public void resetPasswordTConnect(String username){
		try {
			scrollTo(forgotPasswordLink, "Reset Password");
			click(forgotPasswordLink, "Reset Password");
			enterText(usernameId, username, "username");
			switchToFrameToVerifyReCaptcha();
			switchToParentFrame();
			click(continueInResetPasswordPage, "continue in reset password page");
			verifyText(checkYourInbox, "Check your inbox");
		} catch (Exception e) {

		}

		
	}
	public void createNewPasswordPageTconnect() {

		try {
			//switchToParentFrame();
			String NewPassword = password();
			Thread.sleep(4000);
			verifyText(verifyCreateNewPasswordPage, "Reset your password");
			enterText(newPassword, NewPassword, "New Password");
			enterText(reEnterNewPassword, NewPassword, "ReEnter New Password");
			click(SignInToCreateNewPassword, "Sign in button to create new Password");
			click(SignInToCreateNewPassword, "Sign in button to create new Password");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void dashBoardPageTconnect() {
		try {
			verifyText(dashboardPage, "Telstra Connect");
			// elementIsDisplayed(welcomePage);
		} catch (Exception e) {

		}
	}

}
