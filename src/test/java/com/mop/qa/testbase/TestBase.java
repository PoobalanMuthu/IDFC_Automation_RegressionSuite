package com.mop.qa.testbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.opencv.core.Core;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mop.qa.Utilities.ExtentUtility;
import com.mop.qa.Utilities.ReadDataSheet;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;

public class TestBase {

	public static long startTime;
	public static String startTimeUpdate;
	public static long endTime;
	public static long totalTime;
	public static String totalTimeTaken;
	public static String osType = System.getProperty("os.name");
	public static String deviceType = System.getProperty("device.name");
	public static String toolName = "";
	public String appType = "";
	public String RemoteUrl = "";
	public AppiumDriver appiumDriver;
	public RemoteWebDriver remoteDriver;
	public String udid = null;
	public String appium_port = null;
	public String currentTest = "";
	public ReadDataSheet rds = new ReadDataSheet();
	public static String strFilePath = "target\\har_logs\\har.har";
	public static BrowserMobProxy proxy;

	@BeforeSuite
	public void executeSuite(ITestContext ctx) {
		try {
			System.out.println("@ Before Suite");
			ExtentUtility.extent = ExtentUtility.getReporter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getSuiteName(ISuite ist) {

		try {
			System.out.println("" + ist.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void startTestReport(ITestContext ctx) {
		try {
			if (getPropertyValue("Video_solution").equalsIgnoreCase("Yes")) {
				try {
					System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
					// System.loadLibrary("/usr/local/Cellar/opencv/3.4.1_5/share/OpenCV/java/libopencv_java341.dylib");
				} catch (Exception e) {
					System.out.println("In Loading Video Library " + e.getMessage());
				}
			}
			System.out.println("Inside Before Test ........\n");
			String browser = ctx.getCurrentXmlTest().getParameter("browser");
			toolName = ctx.getCurrentXmlTest().getParameter("toolName");
			String exeType = ctx.getCurrentXmlTest().getParameter("ExecutionType");
			String localityType = ctx.getCurrentXmlTest().getParameter("Locality");
			String platform_name = "";
			RemoteUrl = ctx.getCurrentXmlTest().getParameter("RemoteUrl");
			System.out.println("toolname is " + ctx.getCurrentXmlTest().getParameter("toolName"));
			// ExtentUtility.test =
			// ExtentUtility.startTest(ctx.getCurrentXmlTest().getParameter("testname"));
			currentTest= ctx.getCurrentXmlTest().getParameter("testname");
			System.out.println(currentTest+ " is Running");
			String startURL = rds.getValue("DATA", currentTest, "URL");
			
			//Selva Edited-7/2/2020
			
			String write = rds.getValue("DATA", currentTest, "Password");
			
			//String write1 = rds.getRownumber(testCaseName, colHeader)
			
			if (toolName.equalsIgnoreCase("Services")) {
			} else if (toolName.equalsIgnoreCase("Appium")) {
				appium_port = getPort();
				if (exeType.equalsIgnoreCase("Parallel")) {
					appType = ctx.getCurrentXmlTest().getParameter("appType");
					System.out.println("App type is " + ctx.getCurrentXmlTest().getParameter("appType"));
					if (System.getProperty("os.name").contains("Win")) {
						System.out.println("appium parallel");
						udid = ctx.getCurrentXmlTest().getParameter("udid");
						String bootstrap_port = ctx.getCurrentXmlTest().getParameter("Bootstrap_port");
						String chrome_port = ctx.getCurrentXmlTest().getParameter("Chrome_port");
						platform_name = ctx.getCurrentXmlTest().getParameter("platformName");
						// appium_port="4723";
						// startWindowsServer(udid, appium_port, platform_name,
						// localityType);
						Thread.sleep(5000);
						initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName, appType,
								startURL);
					} else {
						if (ctx.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("Android")) {
							udid = ctx.getCurrentXmlTest().getParameter("udid");
							platform_name = ctx.getCurrentXmlTest().getParameter("platformName");
							appType = ctx.getCurrentXmlTest().getParameter("appType");
							if (!localityType.equalsIgnoreCase("Cloud")) {
								startIOSServerForiOSDevice(udid, appium_port);
							}
							initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName,
									appType, startURL);
						} else {
							udid = ctx.getCurrentXmlTest().getParameter("udid");
							platform_name = ctx.getCurrentXmlTest().getParameter("platformName");
							appType = ctx.getCurrentXmlTest().getParameter("appType");
							if (!localityType.equalsIgnoreCase("Cloud")) {
								startIOSServerForiOSDevice(udid, appium_port);
							}
							initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName,
									appType, startURL);

						}
					}
				} else {
					if (toolName.equalsIgnoreCase("Appium")) {
						if (System.getProperty("os.name").contains("Win")) {
							appType = ctx.getCurrentXmlTest().getParameter("appType");
							udid = ctx.getCurrentXmlTest().getParameter("udid");
							platform_name = ctx.getCurrentXmlTest().getParameter("platformName");
							if (!localityType.equalsIgnoreCase("Cloud")) {
								// startWindowsServer(udid, appium_port,
								// platform_name, localityType);
							}
							appium_port = "4723";
							Thread.sleep(5000);
							initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName,
									appType, startURL);

						} else {
							appType = ctx.getCurrentXmlTest().getParameter("appType");
							udid = ctx.getCurrentXmlTest().getParameter("udid");
							platform_name = ctx.getCurrentXmlTest().getParameter("platformName");
							System.out.println("platformName " + ctx.getCurrentXmlTest().getParameter("platformName"));
							if (!localityType.equalsIgnoreCase("Cloud")) {
								// startIOSServerForiOSDevice(udid,
								// appium_port);
							}
							appium_port = "4723";
							Thread.sleep(5000);
							initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName,
									appType, startURL);
						}
					}
				}

			} else
			{
				String browserMobFlag = getPropertyValue("browser_Mob_Proxy");
				if (browserMobFlag != "Yes")
				{
	/*				// Set chrome property
					System.setProperty("webdriver.chrome.driver", "target\\drivers\\chromedriver.exe");
					// Initialize BrowserMob Proxy
					proxy = getProxyServer();
					System.out.println("BrowserMob Proxy running on port: " + proxy.getPort());
					Proxy seleniumProxy = getSeleniumProxy(proxy);
					ChromeOptions options = new ChromeOptions();
					options.setCapability(CapabilityType.PROXY, seleniumProxy);
					remoteDriver = new ChromeDriver(options);
					proxy.newHar("Har File");					
					// Maximize the Chrome Window
					remoteDriver.manage().window().maximize();	*/		
					initiateDriver(localityType, appium_port, browser, RemoteUrl, platform_name, toolName,
							appType, startURL);
				}
				else 
				{

				}

			}
			System.out.println("test name is " + ctx.getCurrentXmlTest().getParameter("testname1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}



	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentUtility.startTest(method.getName());
		System.out.println("Test Method " + method.getName() + "is Starting ");
	}

	@AfterMethod
	protected void afterMethod(ITestResult result) {
		System.out.println("@ After Method");
		if (result.getStatus() == ITestResult.FAILURE) {

			ExtentUtility.getTest().log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentUtility.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			ExtentUtility.getTest().log(LogStatus.PASS, "Test Passed");
		}

		ExtentUtility.getReporter().endTest(ExtentUtility.getTest());
		ExtentUtility.getReporter().flush();
	}

	@AfterTest
	public void afterTest() throws Exception {
		
		System.out.println("@ After Test");
		if (toolName.equalsIgnoreCase("Appium")) {
			if (appiumDriver != null) {
				ExtentUtility.extent.endTest(ExtentUtility.getTest());
				System.out.println("report generation completed");
				System.out.println(System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
						+ "/TestReport.html");
				Thread.sleep(5000);
				// appiumDriver.close();
			}
		} else if (toolName.equalsIgnoreCase("selenium")) 
		{
		
			if (remoteDriver != null) 
			{
				ExtentUtility.extent.endTest(ExtentUtility.getTest());
				Thread.sleep(5000);
				// remoteDriver.close();
			}
		}

	}

	//

	@AfterSuite
	public void finishExecution() throws Exception
	{
 
		System.out.println("	@AfterSuite ...after suite----------------------");
		try {

			if (toolName.equalsIgnoreCase("Appium")) {
				if (appiumDriver != null) {
					// appiumDriver.close();
					appiumDriver.quit();
				}
			} else 
			{
				if (remoteDriver != null) 
				{
					// remoteDriver.close();
					remoteDriver.quit();
				}
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// stopServer();
			ExtentUtility.getReporter().flush();
		}

	}

	public void startIOSServerForAndroidDevice() throws IOException, InterruptedException {
		System.out.println("generate report");
		try {

		} catch (Exception e) {
			// e.printStackTrace();
			return;
		}

	}

	// To start appium call this function
	public void startAppium(String port) throws Exception {
		String chromePort = getPort();
		String bootstrapPort = getPort();
		String command = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js --session-override -p "
				+ port + " --chromedriver-port " + chromePort + " -bp " + bootstrapPort;
		System.out.println(command);
		String output = runCommand(command); // run command on terminal
		System.out.println("output" + output);
	}

	public void startAppiumServerIos(String udid, String port) {
		try {
			String chromePort = getPort();
			String bootstrapPort = getPort();

			CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
			command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);
			command.addArgument("--address", false);
			command.addArgument("127.0.0.1");
			command.addArgument("--port", false);
			command.addArgument(port);
			command.addArgument("--session-override", false);
			command.addArgument("--bootstrap-port", false);
			command.addArgument(bootstrapPort);
			command.addArgument("--chromedriver-port", false);
			command.addArgument(chromePort);
			command.addArgument("-U", false);
			command.addArgument(udid);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			System.out.println("Appium server started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This function will run command on terminal
	public String runCommand(String command) throws InterruptedException, IOException {
		Process proc = null;
		File wd = new File(".");
		proc = Runtime.getRuntime().exec("/bin/bash " + command);
		BufferedReader r = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String line = "";
		String allLine = "";
		while ((line = r.readLine()) != null) {
			allLine = allLine + "" + line + "\n";
			if (line.contains("started on"))
				break;
		}
		return allLine;
	}

	// This will check for free ports
	public String getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		String port = Integer.toString(socket.getLocalPort());
		socket.close();
		return port;
	}

	public void startIOSServerForiOSDevice(String udid, String port) throws IOException, InterruptedException {

		try {
			String chromePort = getPort();
			String bootstrapPort = getPort();
			Thread.sleep(3000);
			CommandLine command = new CommandLine("/usr/local/bin/node");
			command.addArgument("/usr/local/bin/appium", false);
			command.addArgument("--address", false);
			command.addArgument("127.0.0.1");
			command.addArgument("--port", false);
			command.addArgument(port);
			command.addArgument("--session-override", false);
			command.addArgument("--bootstrap", false);
			command.addArgument(bootstrapPort);
			command.addArgument("--udid", false);
			command.addArgument(udid);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);

			executor.execute(command, resultHandler);

			Thread.sleep(20000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
	}

	public void initiateDriver(String localityType, String port, String browser, String RemoteUrl, String platform_name,
			String toolname, String appType, String startURL) {
		try {
			String devicePlatform = null;
			if (toolName.equalsIgnoreCase("Appium")) {
				System.out.println("driver start");
				PageBase pagebaseclass = new PageBase(appiumDriver);
				appiumDriver = pagebaseclass.launchApp(udid, localityType, RemoteUrl, toolname, appType, port,
						platform_name, startURL);
			} else if (toolName.equalsIgnoreCase("selenium")) {
				PageBase pagebaseclass = new PageBase(remoteDriver);
				remoteDriver = pagebaseclass.launchSite(browser, localityType, RemoteUrl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void startWindowsServer(String udid, String port, String platform_name, String localityType)
			throws Exception {
		String chromePort = getPort();
		String bootStrapPort = getPort();
		try {
			if (localityType.equalsIgnoreCase("Grid")) {
				String nodePath_windows = getPropertyValue("nodePath_windows");
				String appiumJSPath_windows = getPropertyValue("appiumJSPath_windows");
				System.out.println("nodePath_windows" + nodePath_windows);
				System.out.println("appiumJSPath_windows" + appiumJSPath_windows);

				CommandLine command = new CommandLine(nodePath_windows);
				command.addArgument(appiumJSPath_windows, false);
				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				DefaultExecutor executor = new DefaultExecutor();
				if (platform_name.equalsIgnoreCase("android")) {
					executor.setExitValue(1);
					executor.execute(command, resultHandler);
					Thread.sleep(10000);
				} else {
					command.addArgument("--udid", false);
					ProcessBuilder pb = new ProcessBuilder();
					Map<String, String> env = pb.environment();
					// Need to be updated *****************************
					env.put("ANDROID_HOME", "C:\\Users\\426205\\AppData\\Local\\Android\\sdk");

					env.put("PATH",
							"/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/android-sdk-macosx:/usr/local/git/bin:/usr/local/Cellar/libimobiledevice/1.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/git/bin:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin:/Users/mspiosteam/libimobiledevice-macosx:/Applications/android-sdk-macosx:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin");
					executor.setExitValue(1);
					executor.execute(command, env, resultHandler);
				}
			} else if (localityType.equalsIgnoreCase("Hub")) {
				String nodePath_windows = getPropertyValue("nodePath_windows");
				String appiumJSPath_windows = getPropertyValue("appiumJSPath_windows");
				System.out.println("nodePath_windows" + nodePath_windows);
				System.out.println("appiumJSPath_windows" + appiumJSPath_windows);

				CommandLine command = new CommandLine(nodePath_windows);
				command.addArgument(appiumJSPath_windows, false);
				command.addArgument("--address", false);
				command.addArgument("127.0.0.1");
				command.addArgument("--port", false);
				command.addArgument(port);
				command.addArgument("--no-reset", false);
				command.addArgument("--session-override", false);
				command.addArgument("-U", false);
				command.addArgument(udid);
				command.addArgument("--bootstrap-port", false);
				command.addArgument(bootStrapPort);
				command.addArgument("--chromedriver-port", false);
				command.addArgument(chromePort);

				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				DefaultExecutor executor = new DefaultExecutor();
				if (platform_name.equalsIgnoreCase("android")) {
					executor.setExitValue(1);
					System.out.println("appium server" + appiumJSPath_windows);
					Thread.sleep(10000);
					executor.execute(command, resultHandler);
					Thread.sleep(10000);
				} else {
					command.addArgument("--udid", false);
					ProcessBuilder pb = new ProcessBuilder();
					Map<String, String> env = pb.environment();
					// Need to be updated *****************************
					env.put("ANDROID_HOME", "C:\\Users\\426205\\AppData\\Local\\Android\\sdk");
					// Need to be updated *****************************
					env.put("PATH",
							"/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/android-sdk-macosx:/usr/local/git/bin:/usr/local/Cellar/libimobiledevice/1.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0/bin:/Users/mspiosteam/.rvm/gems/ruby-2.2.0@global/bin:/Users/mspiosteam/.rvm/rubies/ruby-2.2.0/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/git/bin:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin:/Users/mspiosteam/libimobiledevice-macosx:/Applications/android-sdk-macosx:/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin:/Applications/android-sdk-macosx/tools:/Applications/android-sdk-macosx/platform-tools:/Users/mspiosteam/.rvm/bin");
					executor.setExitValue(1);
					executor.execute(command, env, resultHandler);
				}
			}

			Thread.sleep(30000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void stopServer() {
		try {
			System.out.println("Stop server");
			String filePath = "";
			String filePath1 = "";
			if (System.getProperty("os.name").contains("Win")) {

				filePath = "taskkill /F /IM node.exe";
				Runtime.getRuntime().exec(filePath);
				filePath1 = "taskkill /F /IM chromedriver.exe ";
				Runtime.getRuntime().exec(filePath1);
			} else {

				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall node" });
				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall chrome" });
				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall safari" });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) throws IOException {
		String value = "";
		try {

			FileInputStream fileInputStream = new FileInputStream("data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);
			value = property.getProperty(key);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public static void updateProperty(String updateTime, String startTime) {
		try {
			FileInputStream in = new FileInputStream("report.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream("report.properties");
			props.setProperty("TOTAL_TIME", totalTimeTaken.toString());
			props.setProperty("RUN_STARTED", startTime.toString());
			props.store(out, null);
			out.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static Proxy getSeleniumProxy(BrowserMobProxy proxyServer) 
	{
		return ClientUtil.createSeleniumProxy(proxyServer);
	}

	public static BrowserMobProxy getProxyServer() {
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.setTrustAllServers(true);
		proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		proxy.start();
		return proxy;
	}
	
	
}
