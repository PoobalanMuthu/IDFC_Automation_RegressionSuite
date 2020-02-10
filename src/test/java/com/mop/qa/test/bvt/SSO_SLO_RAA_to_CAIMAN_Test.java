package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.pageobject.SSO_SLO_RAA_to_CAIMAN_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class SSO_SLO_RAA_to_CAIMAN_Test extends TestBase{
	@Test
	public void S6() {
		String username=TestRunner.userName;
		System.out.println(username);
		String password=TestRunner.password;
		System.out.println(password);
		String URL=TestRunner.URL;
		System.out.println(URL);
		String env=TestRunner.environment;
		System.out.println(env);
		String Test_URL="";
		//PROD
		//String Test_URL="https://myid.telstra.com/testapplication";
		//FN1
		//String Test_URL="https://fn1.myid-nonprod.telstra.com/testapplication";
		
		Login_Authentication_TestApplication_Page pageObjLogin;
		pageObjLogin = new Login_Authentication_TestApplication_Page(remoteDriver);
		
		Change_Password_in_MyAccount_and_login_TestApplication_Page pageObjChange;
		pageObjChange = new Change_Password_in_MyAccount_and_login_TestApplication_Page(remoteDriver);
		
		SSO_SLO_RAA_to_CAIMAN_Page pageObj = new SSO_SLO_RAA_to_CAIMAN_Page(remoteDriver);
		try {
			if(env.equalsIgnoreCase("FN1")) {
			Test_URL=getPropertyValue("TestApplicationFN1URL");
			pageObj.enableProxy();
			} else if(env.equalsIgnoreCase("PROD"))
			{
				Test_URL=getPropertyValue("TestApplicationPRODURL");
			}
			pageObjLogin.enterURL(URL);
			Thread.sleep(10000);
			pageObjLogin.enterUserName(username, "Username");
			pageObjChange.clickNext();
			pageObjLogin.enterPassword(password,"Password");
			pageObjChange.clickNext();
			Thread.sleep(5000);
			pageObj.newtab(Test_URL);
			pageObjLogin.afterSignIn();
			pageObj.switchTab(0);
			Thread.sleep(7000);
			if(env.equalsIgnoreCase("FN1")) {
			pageObjChange.logout();
			} else if(env.equalsIgnoreCase("PROD")) {
				pageObjChange.logoutPROD();
			}
			pageObj.switchTab(1);
			Thread.sleep(7000);
			pageObj.pageRefresh();
			pageObjLogin.verifyLogoutMessage();
			pageObj.closeOtherTabs();
		//	pageObjLogin.enterUrl(Test_URL);
			//pageObjLogin.userNameclick();
			//pageObjLogin.enterUserName(username,"User Name");
			//pageObjLogin.enterPassword(password,"Password");
			//pageObjLogin.signInclick();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
