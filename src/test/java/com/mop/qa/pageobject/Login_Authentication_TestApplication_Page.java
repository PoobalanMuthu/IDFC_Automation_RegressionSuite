package com.mop.qa.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.mop.qa.testrunner.TestRunner;

public class Login_Authentication_TestApplication_Page extends PageBase{
	public Login_Authentication_TestApplication_Page(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath = "//label[contains(text(),'Username')]")
	private WebElement userNameLabel;
	
	@FindBy(xpath="//div[@id='tUsername']//input[@id='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath="//div[@id='tPassword']//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign in')]")
	private WebElement signInBtn;
	
	@FindBy(xpath="//h1[contains(text(),'Welcome to Test App 1')]")
	private WebElement welcomeBanner;
	
	@FindBy(xpath="//a[contains(text(),'here')]")
	private WebElement logoutBtn;
	
	@FindBy(xpath="//p[contains(text(),'You have successfully signed out')]")
	private WebElement logoutMessage;
	
	public void enterURL(String url) throws Exception
	{
		enterUrl(url);
	}
	public void userNameclick() throws Exception
	{
		click(userNameLabel,"UserName textbox");
	}
	public void enterUserName(String str,String elementName) throws Exception
	{
		enterText(userNameTextBox,str,elementName);
	}
	public void enterPassword(String str,String elementName) throws Exception
	{
		enterText(passwordTextBox,str,elementName);
	}
	public void signInclick() throws Exception
	{
		click(signInBtn,"Sign In button");
	}
	public void afterSignIn() throws Exception
	{
		if(elementIsDisplayed(welcomeBanner,"Welcome Banner")) {
			System.out.println("Login Successful");
		}
	}
	public void signOut() throws Exception
	{
		click(logoutBtn,"Logout button");
		if(elementIsDisplayed(logoutMessage,"Logout message")) {
			System.out.println("Logout successful");
		}
	}
	public void verifyLogoutMessage() throws Exception
	{
		if(elementIsDisplayed(logoutMessage,"Logout message")) {
			System.out.println("Logout successful");
		}
	}
	
	
	//Selva Edite- 7/2/2020
	public <List> void updateExcel(String newPassword ,int rowcount, int colcount) throws Exception
	{
		
			
		FileInputStream file;		
		file = new FileInputStream("./DataSheet.xls");	
        HSSFWorkbook wb = new HSSFWorkbook(file); //Access the workbook
        HSSFSheet worksheet = wb.getSheetAt(0);        
        Cell Cell = worksheet.getRow(rowcount).getCell(colcount);
        Cell.setCellValue(newPassword);
        Cell Cell1 = worksheet.getRow(rowcount).getCell(colcount);
        file.close(); 
        FileOutputStream output_file =new FileOutputStream(new File("./DataSheet.xls"));  //Open FileOutputStream to write updates
        wb.write(output_file); //write changes
        output_file.close(); 
		
		
		}
	}
	
	

