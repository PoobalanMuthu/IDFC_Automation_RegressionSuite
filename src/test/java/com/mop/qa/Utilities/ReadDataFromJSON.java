package com.mop.qa.Utilities;

import java.io.File;

import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataFromJSON {

	public String testCaseName;
	public String urlName;
	public String userName;
	public String EmailAddress;
	public String EmailRegister;
	public String Password;
	public String ConfirmPassword;
	public String Payload;
	public String data;

	JSONParser parser = new JSONParser();
	/*
	 * public static String data1 = System.getProperty("user.dir"); public
	 * static String data2 = "Video_Auto\\AFL_Optimized\\Read.json";
	 */
	public static String path = "C:\\AutomationWorks\\ArchiveFiles\\Video_Auto\\AFL_Optimized\\Read.json";
	public static JsonNode root = null;
	public static ObjectMapper mapper = null;

	public String getDataFromJson(String fieldName) {
		try {

			mapper = new ObjectMapper();

			root = mapper.readTree(new File(path));
			JsonNode testCaseName = root.at("/scenario1").get(0).get(fieldName);
			data = testCaseName.toString();
			//System.out.println("testCaseName-->" + testCaseName.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;

	}

}
