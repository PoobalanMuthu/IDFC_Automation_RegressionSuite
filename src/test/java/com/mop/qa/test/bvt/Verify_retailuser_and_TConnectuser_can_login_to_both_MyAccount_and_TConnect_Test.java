package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TelstraConnect_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Verify_retailuser_and_TConnectuser_can_login_to_both_MyAccount_and_TConnect_Test extends TestBase{
@Test
public void S11() {
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String URL=TestRunner.URL;
	System.out.println(URL);
	String env=TestRunner.environment;
	System.out.println(env);
	String TConnect_URL="";
	
	try {
		Login_Authentication_TestApplication_Page pageObjLogin;
		pageObjLogin = new Login_Authentication_TestApplication_Page(remoteDriver);
		
		Change_Password_in_MyAccount_and_login_TestApplication_Page pageObjChange;
		pageObjChange = new Change_Password_in_MyAccount_and_login_TestApplication_Page(remoteDriver);
		
		Login_Authentication_TelstraConnect_Page pageObj;
		pageObj = new Login_Authentication_TelstraConnect_Page(remoteDriver);
		
		if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableProxy();
			}
		
		pageObjLogin.enterURL(URL);
		Thread.sleep(10000);
		pageObjLogin.enterUserName(username, "Username");
		pageObjChange.clickNext();
		pageObjLogin.enterPassword(password,"Password");
		pageObjChange.clickNext();
		Thread.sleep(5000);
		if(env.equalsIgnoreCase("FN1")) {
			pageObjChange.logout();
			} else if(env.equalsIgnoreCase("PROD")) {
			pageObjChange.logoutPROD();
			}
		
		//T-connect Sign In
		if(env.equalsIgnoreCase("FN1")) {
			TConnect_URL=getPropertyValue("TConnectFN1URL");
			pageObj.enableWPad();
			} else if(env.equalsIgnoreCase("PROD")) {
				TConnect_URL=getPropertyValue("TConnectPRODURL");	
			}
			pageObj.newtab(TConnect_URL);
			Thread.sleep(14000);
			pageObj.pageRefresh();
			pageObj.enterUserName(username,"User Name");
			pageObj.enterPassword(password,"Password");
			pageObj.signInclick();
			Thread.sleep(15000);
			try {
				pageObj.rateTelstra();
			} catch(Exception ex) {
				
			}
			pageObj.signOut();
			pageObj.closeOtherTabs();
		
	} catch(Exception ex) {
		
	}
}
}
