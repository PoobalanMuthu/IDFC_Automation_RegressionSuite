package com.mop.qa.Utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.*;
public class CSVUtil extends RowListProcessor  {
       
       private String csvFile;
	private String columnToMatch;
	private String stringToMatch;
	public static String value;
       
       public CSVUtil(String csvFileName) {
              this.csvFile=csvFileName;
       }
       public CSVUtil() {
              
       }

       public static CSVUtil loadCSVFile(String csvFileName) {           
              CSVUtil csvUtil = new CSVUtil(csvFileName);
              csvUtil.csvFile=csvFileName;
              return csvUtil;
       }
       public boolean checkifCSVFileExists() {
              return Files.exists(Paths.get(this.csvFile).toAbsolutePath());
       }
       
       public static List<String[]> readCSVFile(String file){
              CsvParserSettings csvParserSettings = new CsvParserSettings();
//              csvParserSettings.selectFields(columName);
              csvParserSettings.getFormat().setDelimiter(',');
              
              csvParserSettings.getFormat().setLineSeparator("\n");
              CsvParser parser = new CsvParser(csvParserSettings);
              String[] row=null;
//              long currentTimeMillis = System.currentTimeMillis();
              List<String[]> csvRowslist = new ArrayList<>();
              try {
                     try (FileReader reader = new FileReader(file)){
                           parser.beginParsing(reader);
                           while((row=parser.parseNext())!=null) {
                                  csvRowslist.add(row);                                 
                           }
                     }
                     
              }
              catch(Exception ex) {
                     System.err.println("Unable to read the CSV File \n"+ex.getMessage());
                     ex.printStackTrace();
              }
              return csvRowslist;
       }
       //Attributes,CurrentXpath
       public static String  readCSVFilewitRowName(String columName) throws FileNotFoundException{
    	   
       try {
    	   String file="session.csv";
    	   String rowtobeMatched="URL";
    	  // FileInputStream fis = new FileInputStream("./DataSheets.csv");
    	   CsvParserSettings csvParserSettings = new CsvParserSettings();
           csvParserSettings.selectFields(columName,rowtobeMatched);
           csvParserSettings.detectFormatAutomatically(','); 
           csvParserSettings.getFormat().setLineSeparator("\n");
           CsvParser parser = new CsvParser(csvParserSettings);
           String[] row=null;
//           long currentTimeMillis = System.currentTimeMillis();
           String value = null;
           try {
			
        	   try {
        		   
            	   FileReader reader = new FileReader("session.csv");
                     parser.beginParsing(reader);
                     Record record;
                     while ((record = parser.parseNextRecord()) != null) {
                    	 
                         String[] recordValues=record.getString(0).split(",");
                         System.out.println("");
     					System.out.println("Get the first record "+recordValues[0] );
     					
                     	/*if(record.getString(rowtobeMatched).equalsIgnoreCase(testCaseName))
							{
                     		value=record.getString(columName).toString(); 
                     		System.out.println("Value from DataSheet: " + record.getString(columName));
							}*/
							}
               
                     return value;
        /*
                  try (FileReader reader = new FileReader("DataSheet.csv")){
                        parser.beginParsing(reader);
                        Record record;
                        while ((record = parser.parseNextRecord()) != null) {
                        	String[] recordValues=record.getString(0).split(",");
                        	
                        	if(record.getString(rowtobeMatched).equalsIgnoreCase(objectName))
							{
                        		value=record.getString(columName).toString(); 
                        		System.out.println("Value from DataSheet: " + record.getString(columName));
							}
							}
                  }*/
                  
           
			} catch (Exception ex) {
				 System.err.println("Unable to read the CSV File \n"+ex.getMessage());
                 ex.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	} catch (Exception e) {
		// TODO: handle exception
	}/*
    	   FileInputStream fis = new FileInputStream("./DataSheets.csv");
    	   CsvParserSettings csvParserSettings = new CsvParserSettings();
           csvParserSettings.selectFields(columName,rowtobeMatched);
           csvParserSettings.detectFormatAutomatically('|'); 
           csvParserSettings.getFormat().setLineSeparator("\n");
           CsvParser parser = new CsvParser(csvParserSettings);
           String[] row=null;
//           long currentTimeMillis = System.currentTimeMillis();
           String value = null;
           try {
        	   

               try{ 
            	   FileReader reader = new FileReader("DataSheets.csv");
                     parser.beginParsing(reader);
                     Record record;
                     while ((record = parser.parseNextRecord()) != null) {
                     	if(record.getString(rowtobeMatched).equalsIgnoreCase(objectName))
							{
                     		value=record.getString(columName).toString(); 
                     		System.out.println("Value from DataSheet: " + record.getString(columName));
							}
							}
               
                     return value;
        
                  try (FileReader reader = new FileReader("DataSheet.csv")){
                        parser.beginParsing(reader);
                        Record record;
                        while ((record = parser.parseNextRecord()) != null) {
                        	String[] recordValues=record.getString(0).split(",");
                        	
                        	if(record.getString(rowtobeMatched).equalsIgnoreCase(objectName))
							{
                        		value=record.getString(columName).toString(); 
                        		System.out.println("Value from DataSheet: " + record.getString(columName));
							}
							}
                  }
                  
           }
           catch(Exception ex) {
                  System.err.println("Unable to read the CSV File \n"+ex.getMessage());
                  ex.printStackTrace();
           }*/
	return value;
    }
           
       public static void main(String[] args) throws Exception {
    	   readCSVFilewitRowName("URL");
       }

}
