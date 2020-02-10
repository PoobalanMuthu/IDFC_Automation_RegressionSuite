package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Login_Authentication_TWHub_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.ResetPassword_for_existing_user_TConnect_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class ResetPassword_for_existing_user_TWHub_Test extends TestBase{
@Test
public void resetPasswordExistingUserTWHub() {
	String URL=TestRunner.URL;
	String username=TestRunner.userName;
	System.out.println(username);
	String password=TestRunner.password;
	System.out.println(password);
	String userNameMailinator=username.split("\\@")[0];
	String env=TestRunner.environment;
	System.out.println(env);
	
	Login_Authentication_TestApplication_Page pageObj;
	pageObj = new Login_Authentication_TestApplication_Page(remoteDriver);
	ResetPassword_for_existing_user_TConnect_Page pageObjReset = new ResetPassword_for_existing_user_TConnect_Page(remoteDriver);
	Login_Authentication_TWHub_Page pageObj17;
	pageObj17 = new Login_Authentication_TWHub_Page(remoteDriver);
	try {
		if(env.equalsIgnoreCase("FN1")) {
		//Local
		//pageObj.enableWPadCTS();	
		//RDP
		pageObj.enableWPad();
		}
		pageObj.enterUrl(URL);
		Thread.sleep(7000);
		pageObj.userNameclick();
		pageObj.enterUserName(username,"User Name");
		pageObjReset.forgotPasswordFlow(userNameMailinator,env);
		Thread.sleep(15000);
		pageObj17.signOut();
	} catch(Exception ex) {
		ex.printStackTrace();
	}
}
}
