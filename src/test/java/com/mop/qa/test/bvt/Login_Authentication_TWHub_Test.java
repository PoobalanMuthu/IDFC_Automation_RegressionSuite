package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Login_Authentication_TWHub_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Login_Authentication_TWHub_Test extends TestBase{
@Test
public void S17() {
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String URL=TestRunner.URL;
	System.out.println(URL);
	String env=TestRunner.environment;
	System.out.println(env);
	
	Login_Authentication_TestApplication_Page pageObj;
	pageObj = new Login_Authentication_TestApplication_Page(remoteDriver);
	
	Login_Authentication_TWHub_Page pageObj17;
	pageObj17 = new Login_Authentication_TWHub_Page(remoteDriver);
	try {
		if(env.equalsIgnoreCase("FN1")) {
		//Local
		//pageObj.disableAll();	
		//RDP
		pageObj.enableWPad();
		}
		pageObj.enterUrl(URL);
		pageObj.userNameclick();
		pageObj.enterUserName(username,"User Name");
		pageObj.enterPassword(password,"Password");
		pageObj.signInclick();
		pageObj17.signOut();
	} catch(Exception ex) {
		
	}
}
}
