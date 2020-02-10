package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TelstraConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Login_Authentication_TelstraConnect_Test extends TestBase{
	@Test
	public void S4() {
		String username=TestRunner.userName;
		System.out.println(username);
		String password=TestRunner.password;
		System.out.println(password);
		String URL=TestRunner.URL;
		System.out.println(URL);
		String env=TestRunner.environment;
		System.out.println(env);
		
		Login_Authentication_TelstraConnect_Page pageObj;
		pageObj = new Login_Authentication_TelstraConnect_Page(remoteDriver);
		try {
			if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableWPad();
			}
			pageObj.enterUrl(URL);
			pageObj.enterUserName(username,"User Name");
			pageObj.enterPassword(password,"Password");
			pageObj.signInclick();
			Thread.sleep(15000);
			try {
				pageObj.rateTelstra();
			} catch(Exception ex) {
				
			}
			pageObj.signOut();
		} catch (Exception ex) {
			
		}

	}
}
