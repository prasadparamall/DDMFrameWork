package com.project_name.utilities;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.project_name.base.*;








public class Utilities extends BaseClass {

	

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}
	
	
	
	
	
	
	@DataProvider(name="Textbox")
	public Object[][] textbox(Method m) throws Exception {

		String sheetName = m.getName();
		int rows = textbox_excel.getRowCount(sheetName);
		int cols = textbox_excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(textbox_excel.getCellData(sheetName, colNum, 1), textbox_excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	
	@DataProvider(name="Checkbox")
	public Object[][] checkBox(Method m) throws Exception {

		String sheetName = m.getName();
		int rows = checkbox_excel.getRowCount(sheetName);
		int cols = checkbox_excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(checkbox_excel.getCellData(sheetName, colNum, 1), checkbox_excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

	
	
	
	
}
