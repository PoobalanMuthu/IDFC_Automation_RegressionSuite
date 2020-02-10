package com.mop.qa.pageobject;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SSO_SLO_RAA_to_CAIMAN_Page extends PageBase{
	public SSO_SLO_RAA_to_CAIMAN_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath="//span[contains(text(),'Sign into My Account')]")
	private WebElement signInLabel;
	
	public void validateSignInLabel() throws Exception {
		isDisplayed(signInLabel,"Sign In label");
	}
	public void newtab(String URL) throws Exception {
		openNewTab(URL);
	}
	
	public void switchTab(int tab) throws Exception {
		swtichTab(tab);
	}
	
	public void pageRefresh() throws Exception {
		commonPageRefresh();
	}
}
