package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Login_Authentication_TelstraConnect_Page;
import com.mop.qa.pageobject.SanityTest_TConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class ScreenValidation_AfterDataSync_Test extends TestBase{
@Test
public void S11() {
	String URL=TestRunner.URL;
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String env=TestRunner.environment;
	System.out.println(env);
	
	SanityTest_TConnect_Page pageObj = new SanityTest_TConnect_Page(remoteDriver);
	Login_Authentication_TelstraConnect_Page loginPageObj;
	loginPageObj = new Login_Authentication_TelstraConnect_Page(remoteDriver);
	
	try {
		if(env.equalsIgnoreCase("FN1")) {
		pageObj.enableWPad();
		}
	pageObj.enterURL(URL);
	pageObj.verifyUserNameWhatsThis();
	pageObj.verifyRememberMeWhatsThis();
	pageObj.verifySignInButton();
	pageObj.verifyResetPasswordLink();
	pageObj.privacyTestLink();
	pageObj.termsOfUserLink();
	loginPageObj.enterUserName(username,"User Name");
	loginPageObj.enterPassword(password,"Password");
	loginPageObj.signInclick();
	Thread.sleep(15000);
	try {
		loginPageObj.rateTelstra();
	} catch(Exception ex) {
		
	}
	loginPageObj.signOut();
	pageObj.verifyClientID();
	pageObj.closeOtherTabs();
	
	} catch(Exception ex) {
		
	}
}
}
