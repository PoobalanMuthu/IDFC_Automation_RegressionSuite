package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.Utilities.MPException;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.pageobject.Change_Password_in_MyAccount_and_login_TestApplication_Page;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Change_Password_in_MyAccount_and_login_TestApplication_Test extends TestBase {
	@Test
	public void S5() throws Exception {
		
		String username=TestRunner.userName;
		System.out.println(username);
		String password=TestRunner.password;
		System.out.println(password);
		String URL=TestRunner.URL;
		System.out.println(URL);
		String newPassword="Change1234";
		String env=TestRunner.environment;
		System.out.println(env);
		
		
		
		
		
		

		
		Login_Authentication_TestApplication_Page pageObjLogin;
		pageObjLogin = new Login_Authentication_TestApplication_Page(remoteDriver);
		
		Change_Password_in_MyAccount_and_login_TestApplication_Page pageObj;
		pageObj = new Change_Password_in_MyAccount_and_login_TestApplication_Page(remoteDriver);
		
		try {
			if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableProxy();
			}
			pageObjLogin.enterURL(URL);
			Thread.sleep(10000);
			pageObjLogin.enterUserName(username, "Username");
			pageObj.clickNext1();
			pageObjLogin.enterPassword(password,"Password");
			pageObj.clickNext();
			pageObj.changePassword(password,newPassword,env);
			//Logging in Test Application
			if(env.equalsIgnoreCase("FN1")) {
			pageObjLogin.enterURL(getPropertyValue("TestApplicationFN1URL"));
			} else if(env.equalsIgnoreCase("PROD")) {
			
				
				//PROD
			pageObjLogin.enterUrl(getPropertyValue("TestApplicationPRODURL"));
			}
			pageObjLogin.userNameclick();
			pageObjLogin.enterUserName(username,"User Name");
			pageObjLogin.enterPassword(newPassword,"Password");
			pageObjLogin.signInclick();
			Thread.sleep(5000);
			pageObjLogin.afterSignIn();
			pageObjLogin.signOut();
			
			//Change the password back to old password
			
			
			ReadDataSheet wds = new ReadDataSheet();
			
			
			wds.getValue("DATA", currentTest, "Password");
			
			int rowcount =wds.getRownumber(currentTest, "Password");
			
			int colcount= wds.getColumnNumber("Password");
						
			pageObjLogin.updateExcel(newPassword ,rowcount, colcount);
			
			/*pageObjLogin.enterURL(URL);
			Thread.sleep(10000);
			pageObjLogin.enterUserName(username, "Username");
			pageObj.clickNext();
			pageObjLogin.enterPassword(newPassword,"Password");
			pageObj.clickNext();
			pageObj.changePassword(newPassword,password,env);
			pageObj.closeOtherTabs();*/
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
