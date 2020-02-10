package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class Change_Password_in_MyAccount_and_login_TestApplication_Page extends PageBase{
	public Change_Password_in_MyAccount_and_login_TestApplication_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	
	@FindBy(xpath="//span[contains(text(),'Sign into My Account')]")
	private WebElement signInLabel;
	
	@FindBy(id="login-maonboarding-btn")
	private WebElement nextBtn;
	
	//////Selva edited -2/6/2020
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement SignIn1st;
	
	
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement SignIn;
	
	//@FindBy(xpath="//i[contains(text(),'Contact details & login')]")
	@FindBy(xpath="//a[contains(text(),'Personal details')]")
	private WebElement personalDetailsButton;
	
	@FindBy(xpath="//div[@id='personal-details-container']//dl[@class='section-content cf password']//a[@class='edit-with-left-line']")
	private WebElement changePasswordLink;
	
	@FindBy(xpath="//a[contains(text(),'Change password')]")
	private WebElement changePasswordLinkFN1;
	
	@FindBy(id="currentPassword")
	private WebElement currentPasswordTextbox;
	
	@FindBy(xpath="//form[@id='changePassword']//input[@id='password']")
	private WebElement newPasswordTextbox;
	
	@FindBy(xpath="//form[@id='changePassword']//input[@id='passwordConfirm']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath="//form[@id='changePassword']//input[@id='save-password-button']")
	private WebElement saveButton;
	
	@FindBy(xpath="//div[@id='personal-details-page']//span[contains(text(),'Password successfully updated')]")
	private WebElement passwordSuccessfulMessage;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	private WebElement logoutButton;
	
	@FindBy(xpath="//div[contains(text(),'Logout')]")
	private WebElement logoutButtonPROD;
	
	@FindBy(xpath="//h1[contains(text(),'How did you go?')]")
	private WebElement howDidItGoPopUp;
	
	@FindBy(xpath="//a[@class='fancybox-item fancybox-close']")
	private WebElement howDidItGoPopUpClose;
	
	@FindBy(xpath="//h1[contains(text(),'You are now signed out of your account')]")
	private WebElement signOutSuccessfulMessage;
	
	@FindBy(xpath="//h1[contains(text(),'TELSTRA 24x7')]")
	private WebElement dashboardLabel;
	
	@FindBy(xpath="//div[@class='profile-settings dropdown']//a[contains(text(),'Settings')]")
	private WebElement settingDropDown;
	
	@FindBy(xpath="//div[@class='profile-settings dropdown']//a[contains(text(),'Personal Details')]")
	private WebElement personalDetails;
	
	public void verifyHomePage(String env) throws Exception {
		if(env.equalsIgnoreCase("FN1")) {
		isDisplayed(dashboardLabel,"My Account Dashboard");
		}else if(env.equalsIgnoreCase("PROD")) {
			isDisplayed(logoutButtonPROD,"My Account Dashboard");
		}
	}
	
	public void clickNext1() throws Exception {
		click(SignIn1st,"SignIn1 button");
	}
	
	
	
	
	public void clickNext() throws Exception {
		
		Thread.sleep(5000);
		click(SignIn,"SignIn2 button");
	}
	
	public void changePassword(String currentPassword, String newPassword, String environment) throws Exception {
		if(environment.equalsIgnoreCase("FN1") ) {
		//FN1
		click(personalDetailsButton,"Personal details");
		scrollTo(changePasswordLinkFN1,"Edit button");
		click(changePasswordLinkFN1,"Edit button");
		enterText(currentPasswordTextbox,currentPassword,"Current Password textbox");
		enterText(newPasswordTextbox,newPassword,"New Password textbox");
		enterText(confirmPasswordTextbox,newPassword,"Current Password textbox");
		click(saveButton,"Save button");
		isDisplayed(passwordSuccessfulMessage,"Password successfully updated message");
		logout();
		} else if(environment.equalsIgnoreCase("PROD")) {
		//PROD
		click(settingDropDown,"Settings Dropdown");
		click(personalDetails,"Personal Details");
		clickByJse(changePasswordLink,"Edit button");
		enterText(currentPasswordTextbox,currentPassword,"Current Password textbox");
		enterText(newPasswordTextbox,newPassword,"New Password textbox");
		enterText(confirmPasswordTextbox,newPassword,"Current Password textbox");
		click(saveButton,"Save button");
		Thread.sleep(5000);
		isDisplayed(passwordSuccessfulMessage,"Password successfully updated message");
		logout();
		}
	}
	public void logoutPROD() throws Exception {
		click(logoutButtonPROD,"Logout button");
		Thread.sleep(15000);
		if(elementIsDisplayed(howDidItGoPopUp)){
			click(howDidItGoPopUpClose,"Close button");
		}
		isDisplayed(signOutSuccessfulMessage,"Signout successful message");
	}
	public void logout() throws Exception {
		click(logoutButton,"Logout button");
		Thread.sleep(15000);
		if(elementIsDisplayedWithoutResult(howDidItGoPopUp,"How did it go Popup")){
			click(howDidItGoPopUpClose,"Close button");
		}
		isDisplayed(signOutSuccessfulMessage,"Signout successful message");
	}
}
