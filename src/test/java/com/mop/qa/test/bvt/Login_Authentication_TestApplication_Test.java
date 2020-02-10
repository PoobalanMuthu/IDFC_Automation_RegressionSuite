package com.mop.qa.test.bvt;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.itextpdf.text.log.SysoCounter;
import com.mop.qa.pageobject.Login_Authentication_TestApplication_Page;
import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;

public class Login_Authentication_TestApplication_Test extends TestBase{
	@Test
	public void S3() throws Exception {
		
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
		try {
			if(env.equalsIgnoreCase("FN1")) {
			pageObj.enableWPad();
			}
			pageObj.enterUrl(URL);
			pageObj.userNameclick();
			pageObj.enterUserName(username,"User Name");
			pageObj.enterPassword(password,"Password");
			pageObj.signInclick();
			Thread.sleep(5000);
			pageObj.afterSignIn();
			pageObj.signOut();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
