package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.SSO_SLO_RAA_to_CAIMAN_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class SSO_SLO_CAIMAN_to_RAA_Test extends TestBase{
@Test
public void S7() {
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String URL=TestRunner.URL;
	System.out.println(URL);
	String env=TestRunner.environment;
	System.out.println(env);
	
	Login_Authentication_TestApplication_Page pageObjLogin;
	pageObjLogin = new Login_Authentication_TestApplication_Page(remoteDriver);
	
	Change_Password_in_MyAccount_and_login_TestApplication_Page pageObjChange;
	pageObjChange = new Change_Password_in_MyAccount_and_login_TestApplication_Page(remoteDriver);
	
	SSO_SLO_RAA_to_CAIMAN_Page pageObj = new SSO_SLO_RAA_to_CAIMAN_Page(remoteDriver);
	
	try {
		String MyAccount_URL=getPropertyValue("MyAccountURL");
		if(env.equalsIgnoreCase("FN1")) {
		pageObj.enableProxy();
		}
		pageObjLogin.enterURL(URL);
		Thread.sleep(7000);
		pageObjLogin.enterUserName(username,"User Name");
		pageObjLogin.enterPassword(password,"Password");
		pageObjLogin.signInclick();
		Thread.sleep(5000);
		pageObjLogin.afterSignIn();
		Thread.sleep(5000);
		pageObj.newtab(MyAccount_URL);
		Thread.sleep(7000);
		pageObjChange.verifyHomePage(env);
		Thread.sleep(7000);
		pageObj.switchTab(0);
		pageObjLogin.signOut();
		Thread.sleep(7000);
		pageObj.switchTab(1);
		pageObj.pageRefresh();
		pageObj.validateSignInLabel();
		pageObj.closeOtherTabs();
		//pageObjChange.logout();
		
		
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}
}
