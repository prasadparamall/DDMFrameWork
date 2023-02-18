package com.project_name.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static FileInputStream fis;
	public static Properties pro;
	
	
	public static Object browser(String key) throws IOException
	{
	fis = new FileInputStream(Paths.configu);
	pro = new Properties();
	pro.load(fis);
	return pro.get(key);
	}
	
	public static String textBox(String key) throws IOException
	{
	fis = new FileInputStream(Paths.textbox_pro);
	pro = new Properties();
	pro.load(fis);
	return pro.get(key).toString();
	}
	
	public static String checkBox(String key) throws IOException
	{
	fis = new FileInputStream(Paths.checkbox_pro);
	pro = new Properties();
	pro.load(fis);
	return pro.get(key).toString();
	}

}
