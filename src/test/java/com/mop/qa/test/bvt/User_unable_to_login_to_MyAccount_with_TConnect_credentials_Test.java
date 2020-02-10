package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.User_unable_to_login_to_MyAccount_with_TConnect_credentials_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class User_unable_to_login_to_MyAccount_with_TConnect_credentials_Test extends TestBase {
@Test
public void S9() {
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
	User_unable_to_login_to_MyAccount_with_TConnect_credentials_Page pageObj;
	pageObj = new User_unable_to_login_to_MyAccount_with_TConnect_credentials_Page(remoteDriver);
	try {
		if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableProxy();
		}
		pageObjLogin.enterURL(URL);
		Thread.sleep(10000);
		pageObjLogin.enterUserName(username, "Username");
		pageObjChange.clickNext();
		pageObjLogin.enterPassword(password,"Password");
		pageObjChange.clickNext();
		pageObj.verifyLoginErrorMessage();
	} catch(Exception ex) {
		
	}
}
}
