package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class CheckGetnadaMailbox extends PageBase {

	public CheckGetnadaMailbox(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	String URL = "https://getnada.com/msg";
	@FindBy(xpath = "//span[contains(text(),'Add Inbox')]")
	private WebElement addInbox;

	@FindBy(className = "user_name")
	private WebElement user_name;
	
	@FindBy(xpath = "//a[contains(text(),'Accept')]")
	private WebElement addMailbox;

	@FindBy(xpath = "//button[contains(text(),'OK, accept all')]")
	private WebElement acceptAll;
	
	//PageObject for TW-Hub
	@FindBy(xpath = "//div[@class='subject bold'][contains(text(),'tw-hub: Reset your password')]")
	private WebElement resetPasswordMailTwHub;

	@FindBy(xpath = "//p[contains(text(),'Your tw-hub password reset')]")
	private WebElement middleOfMailTwHub;

	@FindBy(partialLinkText = "Reset my password")
	private WebElement ResetMyPasswordButtonTwHub;
	
	@FindBy(xpath = "//div[@class='subject bold'][contains(text(),'tw-hub: Your password has been updated')]")
	private WebElement passwordUpdationMailTwHub;
	
	@FindBy(xpath = "//p[contains(text(),'Your tw-hub password')]")
	private WebElement PasswordUpdationMailTwHub;
	
	//PageObject for T-connect
	@FindBy(xpath = "//div[@class='subject bold'][contains(text(),'Reset your Telstra Connect password')]")
	private WebElement resetPasswordMailTconnect;

	@FindBy(xpath = "//span[contains(text(),'Forgot your password?')]")
	private WebElement middleOfMailTconnect;

	@FindBy(xpath = "//span[contains(text(),'Sign in')]")
	private WebElement ResetMyPasswordButtonTconnect;
	
	@FindBy(xpath = "//div[@class='subject bold'][contains(text(),'Telstra Connect password updated')]")
	private WebElement passwordUpdationMailTconnect;
	
	@FindBy(xpath = "//span[contains(text(),'Your password has been updated')]")
	private WebElement PasswordUpdationMailTconnect;
	
	String tempMail = "//span[contains(text(),'Temp mail:')]";
	/*
	@FindBy(xpath = "//span[contains(text(),'Temp mail:')]")
	private WebElement tempMail;
	*/
	
	String frame = "idIframe";

	@SuppressWarnings("unused")
	public void MailboxAndClickResetPasswordButtonTwHub(String usernameGetnada) {
		try {
			openNewTab(URL);
			click(addInbox, "Add InBox In getNada page");
			enterText(user_name, usernameGetnada, "username entered");
			click(addMailbox, "accept button to add mailbox");
			click(resetPasswordMailTwHub, "Reset Password Mail");
			Thread.sleep(5000);
			int frames = remoteDriver.findElements(By.xpath("//iframe")).size();
			System.out.println("windo" + frames);
			for (int i = 1; i < frames; i++) {
				remoteDriver.switchTo().frame(i);
				scrollTo(middleOfMailTwHub, "Sign in button");
				click(ResetMyPasswordButtonTwHub, "Sign in Button");
				switchToWindowTitle();
				break;
			}
		} catch (Exception e) {
		
		}
	}

	public void mailBoxCheckPasswordUpdationMailTwHub(String username) {
		try {
		
			switchToParentWindowTitle();
			switchToParentFrame();
			String lowerCaseUsername = username.toLowerCase();
			String userNameXpath = "//span[contains(text(),'"+lowerCaseUsername+"')]";
			click(userNameXpath, "username in getnada page");
			scrollUp(tempMail);
			//click(acceptAll, "cookies");
			click(passwordUpdationMailTwHub, "Password updation mail");
			int frames = remoteDriver.findElements(By.xpath("//iframe")).size();
			System.out.println("windo" + frames);
			for (int i = 1; i < frames; i++) {
				remoteDriver.switchTo().frame(i);
				scrollTo(PasswordUpdationMailTwHub, "Password updation mail");
			}
			
		} catch (Exception e) {

		}

	}
	@SuppressWarnings("unused")
	public void MailboxAndClickResetPasswordButtonTconnect(String usernameGetnada) {
		try {
			openNewTab(URL);
			click(addInbox, "Add InBox In getNada page");
			enterText(user_name, usernameGetnada, "username entered");
			click(addMailbox, "accept button to add mailbox");
			click(resetPasswordMailTconnect, "Reset Password Mail");
			Thread.sleep(5000);
			int frames = remoteDriver.findElements(By.xpath("//iframe")).size();
			System.out.println("windo" + frames);
			for (int i = 1; i < frames; i++) {
				remoteDriver.switchTo().frame(i);
				scrollTo(middleOfMailTconnect, "Reset my password button");
				click(ResetMyPasswordButtonTconnect, "Reset My Password Button");
				switchToWindowTitle();
				break;
			}
		} catch (Exception e) {
		}
	}

	public void checkPasswordUpdationMailTconnect(String username) {
		try {
		
			switchToParentWindowTitle();
			switchToParentFrame();
			String lowerCaseUsername = username.toLowerCase();
			String userNameXpath = "//span[contains(text(),'"+lowerCaseUsername+"')]";
			click(userNameXpath, "username in getnada page");
			scrollUp(tempMail);
			//click(acceptAll, "cookies");
			Thread.sleep(3000);
			click(passwordUpdationMailTconnect, "Password updation mail");
			int frames = remoteDriver.findElements(By.xpath("//iframe")).size();
			System.out.println("windo" + frames);
			for (int i = 1; i < frames; i++) {
				remoteDriver.switchTo().frame(i);
				scrollTo(PasswordUpdationMailTconnect, "Password updation mail");
			}
		} catch (Exception e) {
		}
	}
}
