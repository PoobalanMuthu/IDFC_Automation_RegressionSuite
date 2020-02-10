package com.mop.qa.Utilities;

public class TestParameter {
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName1) {
		this.testName = testName1;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppType() {
		return appType;
	}
	
	/*3/5/2019*/
	/*Start*/
	private String userName;
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public String getUserName() {
		return userName;
	}
	private String password;
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
	private String URL;
	public void setURL(String URL) {
		this.URL=URL;
	}
	public String getURL() {
		return URL;
	}
	private String environment;
	public void setEnvironment(String environment) {
		this.environment=environment;
	}
	public String getEnvironment() {
		return environment;
	}
	/*End*/

	public void setTestClass(String testClass1) {
		testClass = testClass1;
	}
	public String getExcetionFlag() {
		return ExcetionFlag;
	}

	public void setExcetionFlag(String excetionFlag) {
		ExcetionFlag = excetionFlag;
	}
	private String toolName;
	private String testName;
	private String browser;
	private String appType;
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setRemoteUrl(String remoteUrl){
		this.remoteUrl=remoteUrl;
	}


	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	private String locality;
	private String remoteUrl;
	private String executionType;
	private String platformName;
	private String udid;
	private String deviceName;
	private String port;
	private String no_of_devices;
	private String bootstrap_port;
	private String No_of_Instances;
	
	public String getNo_Of_Instances() {
		String NoOfInstances="";
		if(No_of_Instances.equalsIgnoreCase("One")){
			NoOfInstances="1";
		}else if(No_of_Instances.equalsIgnoreCase("Two")){
			NoOfInstances="2";
		}else if(No_of_Instances.equalsIgnoreCase("Three")){
			NoOfInstances="3";
		}
		return NoOfInstances;
	}

	public void setNo_Of_Instances(String no_of_Instances) {
		No_of_Instances = no_of_Instances;
	}

	
	public String getBootstrap_port() {
		return bootstrap_port;
	}

	public void setBootstrap_port(String bootstrap_port) {
		this.bootstrap_port = bootstrap_port;
	}

	public String getRemoteUrl(){
		return remoteUrl;
	}

	public String getChrome_port() {
		return chrome_port;
	}

	public void setChrome_port(String chrome_port) {
		this.chrome_port = chrome_port;
	}

	
	
	
	private String chrome_port;
	
	public String getNo_of_devices() {
		return no_of_devices;
	}

	public void setNo_of_devices(String no_of_devices) {
		this.no_of_devices = no_of_devices;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}
	private String testClass;
	private String ExcetionFlag;

	
	 public void TestParameter1(String testName1, String testClass1) {
	 this.testName = testName1; this.testClass = testClass1; }
	 
	private String OriName;
	public void setTestName1(String oriName) {
        OriName = oriName;
 }

public String getTestName1() {
        return OriName;
 }

}
