package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.SSO_from_AFL_to_MyAccount_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class SSO_from_AFL_to_MyAccount_Test extends TestBase{
	@Test
	public void SSO_AFL_MyAccount()
	{
		String username=TestRunner.userName;
		System.out.println(username);
		String password=TestRunner.password;
		System.out.println(password);
		String URL=TestRunner.URL;
		System.out.println(URL);
		String env=TestRunner.environment;
		System.out.println(env);
		String testApplicationURL="";
		
		SSO_from_AFL_to_MyAccount_Page pageObj = new SSO_from_AFL_to_MyAccount_Page(remoteDriver);
		try {
			String myAccountURL=getPropertyValue("MyAccountURL");
			if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableProxy();
			testApplicationURL=getPropertyValue("TestApplicationFN1URL");
			} else if(env.equalsIgnoreCase("PROD")) {
				testApplicationURL=getPropertyValue("TestApplicationPRODURL");
			}
			Thread.sleep(14000);
			pageObj.enterURL(URL);
			Thread.sleep(12000);
			pageObj.login(username, password,env);
			pageObj.myAccount(env,myAccountURL);
			pageObj.testApplication(env,testApplicationURL);
			pageObj.closeOtherTabs();
		} catch(Exception ex) {
			
		}
	}
}
