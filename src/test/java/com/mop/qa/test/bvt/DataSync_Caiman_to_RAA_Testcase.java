package com.mop.qa.test.bvt;

import org.testng.annotations.Test;
import com.mop.qa.pageobject.CheckGetnadaMailbox;
import com.mop.qa.pageobject.TconnectPages;
import com.mop.qa.testbase.TestBase;

public class DataSync_Caiman_to_RAA_Testcase extends TestBase {
	
	@Test
	public void DataSync_Caiman_to_RAA(){
		try {
			String url = rds.getValue("DATA", currentTest, "URL");
			String username = rds.getValue("DATA", currentTest, "Username");
			String user[] = username.split("@");
			String usernameGetnada = user[0];
			System.out.println(usernameGetnada);
			
			TconnectPages goTo = new TconnectPages(remoteDriver); 
			CheckGetnadaMailbox open = new CheckGetnadaMailbox(remoteDriver);
			goTo.tconnectLoginPage(url);
			goTo.resetPasswordTConnect(username);
			open.MailboxAndClickResetPasswordButtonTconnect(usernameGetnada);
			goTo.createNewPasswordPageTconnect();
			goTo.dashBoardPageTconnect();
			open.checkPasswordUpdationMailTconnect(usernameGetnada);
		} catch (Exception e) {
		}
		
		
	}

}
