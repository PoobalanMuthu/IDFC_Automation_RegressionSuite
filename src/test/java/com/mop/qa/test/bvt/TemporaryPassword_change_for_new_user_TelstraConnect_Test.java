package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.TemporaryPassword_change_for_new_user_TelstraConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class TemporaryPassword_change_for_new_user_TelstraConnect_Test extends TestBase {
	@Test
	public void S13a() {
		String username=TestRunner.userName;
		System.out.println(username);
		String password=TestRunner.password;
		System.out.println(password);
		String URL=TestRunner.URL;
		System.out.println(URL);
		String env=TestRunner.environment;
		System.out.println(env);
		
		TemporaryPassword_change_for_new_user_TelstraConnect_Page pageObj;
		pageObj = new TemporaryPassword_change_for_new_user_TelstraConnect_Page(remoteDriver);
		
		try {
			if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableWPad();
			}
			pageObj.enterUrl(URL);
			pageObj.enterUserName(username,"User Name");
			pageObj.enterPassword(password,"Password");
			pageObj.signInclick();
			pageObj.verifyResetPassword(password);
			pageObj.enterUserName(username,"User Name");
			pageObj.enterPassword("Pass1234","Password");
			pageObj.signInclick();
			Thread.sleep(35000);
			pageObj.signOut();
			
		} catch (Exception ex) {
			
		}

	}
}
