package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.Specific_whitelist_user_login_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Specific_whitelist_user_login_Test extends TestBase{
	@Test
	public void whitelist() {
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
	Specific_whitelist_user_login_Page pageObj;
	pageObj = new Specific_whitelist_user_login_Page(remoteDriver);
	
	try {
		if(env.equalsIgnoreCase("FN1")) {
		pageObj.enableProxy();
		}
		Thread.sleep(14000);
		pageObjLogin.enterURL(URL);
		Thread.sleep(10000);
		pageObjLogin.enterUserName(username, "Username");
		pageObjChange.clickNext();
		pageObjLogin.enterPassword(password,"Password");
		pageObj.clickSignIn();
		Thread.sleep(5000);
		if(env.equalsIgnoreCase("FN1")) {
		pageObjChange.logout();
		} else if(env.equalsIgnoreCase("PROD")) {
		pageObjChange.logoutPROD();
		}
		pageObj.closeOtherTabs();
	} catch(Exception ex) {
		
	}
	}
}
