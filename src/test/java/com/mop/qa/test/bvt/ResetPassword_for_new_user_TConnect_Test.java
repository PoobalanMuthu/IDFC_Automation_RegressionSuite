package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Login_Authentication_TelstraConnect_Page;
import com.mop.qa.pageobject.ResetPassword_for_existing_user_TConnect_Page;
import com.mop.qa.pageobject.SanityTest_TConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class ResetPassword_for_new_user_TConnect_Test extends TestBase {

@Test
public void S14() {
	String URL=TestRunner.URL;
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String userNameMailinator=username.split("\\@")[0];
	String env=TestRunner.environment;
	System.out.println(env);
	
	SanityTest_TConnect_Page tConnectpageObj = new SanityTest_TConnect_Page(remoteDriver);
	Login_Authentication_TelstraConnect_Page loginPageObj;
	loginPageObj = new Login_Authentication_TelstraConnect_Page(remoteDriver);
	ResetPassword_for_existing_user_TConnect_Page pageObj = new ResetPassword_for_existing_user_TConnect_Page(remoteDriver);
	
	try {
		if(env.equalsIgnoreCase("FN1")) {
		//Local
		//pageObj.enableWPadCTS();
		//RDP
		pageObj.enableWPad();
		}
		tConnectpageObj.enterURL(URL);
		Thread.sleep(7000);
		loginPageObj.enterUserName(username,"User Name");
		pageObj.forgotPasswordFlow(userNameMailinator,env);
		Thread.sleep(15000);
		loginPageObj.signOut();
	} catch(Exception ex) {
		
	}
}


}
