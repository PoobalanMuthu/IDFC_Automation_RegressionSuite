package com.mop.qa.test.bvt;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.TreatmentTest_Page;
import com.mop.qa.testbase.TestBase;

public class TreatmentTest_Test extends TestBase{
	@Test
	public void S2() {
		TreatmentTest_Page pageObj = new TreatmentTest_Page(remoteDriver);
		try {
			//DataGOV
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/identity/scim/v2/Users/");
			pageObj.Error1();
			
			//FBS API 1
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/presentation/telstra-mail/v1/mailbox-registration/create");
			pageObj.Error2();
			
			//FBS API 2
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/presentation/telstra-mail/v1/mailbox-registration/availability");
			pageObj.Error2();
			
			//FBS API 3
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/v1/mailbox-registration/create");
			pageObj.Error2();
			
			//FBS API 4
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/v1/mailbox-registration/availability");
			pageObj.Error2();

			//Active(FTL)
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/identity/as/authorization.oauth2?response_type=code&client_id=testapp&redirect_uri=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Fpa%2Foidc%2Fcb&state=eyJ6aXAiOiJERUYiLCJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiYSIsInN1ZmZpeCI6IlNCb1dnYi4xNTM5MjE0NjMwIn0..ps9ht_bmctXYoTocSc6L_Q.ehPnBQzOrfTUi-feRLhgDJvX6szg0bdQLDhpT6Qi9grJH14gtUoXSwiI1YYGdZADtIRIn90ph1C-X6YXJ09ru8_NqwcqHY3Afs4AqayMHMQ.YdxnEiB_eBNcueIz6-iIDQ&nonce=eSZro5oPaBb0WmZaJHxF9S1a-gpGKYoSBdV85wtlbSk&scope=openid&vnd_pi_requested_resource=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Ftestapplication&vnd_pi_application_name=TestApplication&activateMailbox=true");
			pageObj.ActiveFTL();
			
			//Change Password
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/identity/as/authorization.oauth2?response_type=code&client_id=testapp&redirect_uri=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Fpa%2Foidc%2Fcb&state=eyJ6aXAiOiJERUYiLCJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiYSIsInN1ZmZpeCI6IndtN2tSZC4xNTM5MjE0NzY1In0..vs6QhDmDHTWcM0dIizgKbQ.tlMHqRJO0ffvHsgi70CaHttPUSGwFIqKil5QBU3XzvQaw4l4R1Oqb6uP-NW9Fbm4ukh2UZ56UXsgSO2MUdJds7UmPQknkUownzHnNSOXeIY.PiBYSkiuZE7VM4WyTu5svA&nonce=exheXVt31fynSSXvJXfWqdROJnMVLSQEqw7aMcWu67I&scope=openid&vnd_pi_requested_resource=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Ftestapplication&vnd_pi_application_name=TestApplication&ChangePassword=true&myaccount=true");
			pageObj.changePassword();
			
			//Reset Password Phase2
			pageObj.enterURL("https://fn1.myid-nonprod.telstra.com/identity/as/authorization.oauth2?response_type=code&client_id=testapp&redirect_uri=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Fpa%2Foidc%2Fcb&state=eyJ6aXAiOiJERUYiLCJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiYSIsInN1ZmZpeCI6InJDMDM1WS4xNTM5MjE1MjQyIn0..Udipka34SaWn3VjyQN26Ug.ognnfTqf-ZE0sRwewAG0ShvFM9c0RBGPLFQFapFTwLfZEYkABK0HNUfHAD20lQXBoWVvZkMNnJHNEcM3MsimeYkhZi1it9M7r12ABbwwEYQ.BGUMK0RU0TvpgBc83uJIWQ&nonce=J6PHm9FuASZZAJZHzZBV5QlUevpm0rZ1SjRzj88NtLs&scope=openid&vnd_pi_requested_resource=https%3A%2F%2Ffn1.myid-nonprod.telstra.com%2Ftestapplication&vnd_pi_application_name=TestApplication&forgottenPassword=true");
			pageObj.resetPasswordPhase2();
			
			//Reset Password Link
			pageObj.enterURL("https://myid.telstra.com/identity/as/authorization.oauth2?response_type=code&client_id=24x7.mobile.android&redirect_uri=com.telstra.mobile.android.mytelstra://oauth2redirect&scope=openid%20m2w");
			pageObj.Error1();
			
		} catch(Exception ex) {

		}
	}
}
