package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Verify_Retail_user_unable_to_login_TelstraConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Verify_Retail_user_unable_to_login_TelstraConnect_Test extends TestBase {
	@Test
	public void S8() {
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String URL=TestRunner.URL;
	System.out.println(URL);
	String env=TestRunner.environment;
	System.out.println(env);
	
	Verify_Retail_user_unable_to_login_TelstraConnect_Page pageObj;
	pageObj = new Verify_Retail_user_unable_to_login_TelstraConnect_Page(remoteDriver);
	
	try {
		if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableWPad();
		}
		pageObj.enterUrl(URL);
		pageObj.enterUserName(username,"User Name");
		pageObj.enterPassword(password,"Password");
		pageObj.signInclick();
		Thread.sleep(15000);
		pageObj.noAccount();
		
	} catch (Exception ex) {
		
	}
	}
}
