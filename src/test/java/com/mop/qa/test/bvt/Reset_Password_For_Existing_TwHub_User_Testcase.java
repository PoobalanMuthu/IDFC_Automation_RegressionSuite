package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.CheckGetnadaMailbox;
import com.mop.qa.pageobject.TwHubPages;
import com.mop.qa.testbase.TestBase;

public class Reset_Password_For_Existing_TwHub_User_Testcase extends TestBase {

	@Test
	public void testcaseToVerfyReset() {
		try {
			String url = rds.getValue("DATA", currentTest, "URL");
			String userName = rds.getValue("DATA", currentTest, "Username");
			String user[] = userName.split("@");
			String usernameGetnada = user[0];
			System.out.println(userName);
			TwHubPages goTo = new TwHubPages(remoteDriver);
			CheckGetnadaMailbox open = new CheckGetnadaMailbox(remoteDriver);
			goTo.twhubLoginPage(url);
			goTo.resetPassword(userName);
			open.MailboxAndClickResetPasswordButtonTwHub(usernameGetnada);
			goTo.createNewPasswordPageTwHub();
			goTo.welcomePageTwHub();
			open.mailBoxCheckPasswordUpdationMailTwHub(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
