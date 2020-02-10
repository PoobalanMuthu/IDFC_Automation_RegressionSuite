package com.mop.qa.Utilities;


import java.io.File;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.mop.qa.testbase.TestBase;
import com.mop.qa.testrunner.TestRunner;


public class WriteDataSheet extends ReadDataSheet {
	
	public int getRownumber(String testCaseName, String colHeader) throws MPException {
		int rownumber = 0;
		rowCount = ws.getLastRowNum();

		for (int j = 1; j <= rowCount; j++) {
			HSSFRow row = ws.getRow(j);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
				rownumber = j;
				
				//System.out.println(rownumber);
				break;
			}

		}
		if (rownumber == 0) {
			throw new MPException("Class Entry missing in DataSheet");
		}

		getColumnNumber(colHeader);
		return rownumber;
	}

			
	
	
	
	public static void main(String[] args) throws Exception  {
	
		
		FileInputStream file;		
		file = new FileInputStream("./DataSheet.xls");	
        HSSFWorkbook wb = new HSSFWorkbook(file); //Access the workbook
        HSSFSheet worksheet = wb.getSheetAt(0);
        //Cell Cell = worksheet.getRow(getRownumber(Password, Password)).getCell(2);
       // Cell.setCellValue("Change1234");
       /* Cell Cell1 = worksheet.getRow(17).getCell(2);
        file.close(); 
        FileOutputStream output_file =new FileOutputStream(new File("./DataSheet.xls"));  //Open FileOutputStream to write updates
        wb.write(output_file); //write changes
        output_file.close(); */
		
	
	}

}
