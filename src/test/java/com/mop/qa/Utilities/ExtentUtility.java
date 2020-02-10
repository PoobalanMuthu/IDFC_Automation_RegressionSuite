package com.mop.qa.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentUtility {
	
	public static ExtentTest test ;
	public static ExtentReports extent ;
	public static String reportFolder ="";
	@SuppressWarnings("rawtypes")
	static Map extentTestMap = new HashMap();
	
	
	 public synchronized static ExtentReports getReporter() {
	        if (extent == null) {
	        	SimpleDateFormat sdfDateReport = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// dd/MM/yyyy
				Date now = new Date();
				 reportFolder = "HtmlReport_" + sdfDateReport.format(now);
	        	String reports=new File("ReportGenerator/"+reportFolder+"/TestReport.html").getPath();
	        	 //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(s);
				 extent = new ExtentReports(reports, true,Locale.ENGLISH);
				 extent.addSystemInfo("Environment","Environment Name");
				 extent.addSystemInfo("Host Name", "Automation Report");
				 extent.addSystemInfo("Device Name", "2.46");
				 extent.addSystemInfo("Device UDID", "03157df35375a910");
				 extent.addSystemInfo("Application Build", "05.01.40739");
				 extent.assignProject("AFL");
				/* final PackageManager pm = getPackageManager();
				 String apkName = "example.apk";
				 String fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;        
				 PackageInfo info = pm.getPackageArchiveInfo(fullPath, 0);
				 Toast.makeText(this, "VersionCode : " + info.versionCode + ", VersionName : " + info.versionName , Toast.LENGTH_LONG).show();
				*/
			        
			        
	        }
	        
	        return extent;
	    }
	 public static synchronized ExtentTest getTest() {
	        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	    }

	    public static synchronized void endTest() {
	        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	    }

	    public static synchronized ExtentTest startTest(String testName) {
	        return startTest(testName, "");
	    }

	    @SuppressWarnings("unchecked")
		public static synchronized ExtentTest startTest(String testName, String desc) 
	    {
	        ExtentTest test = extent.startTest(testName, desc);
	        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
	        return test;
	    }	
	
}
