package com.project_name.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project_name.utilities.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties pro;
	public ExcelReader textbox_excel = new ExcelReader(Paths.textbox_excel);
	public ExcelReader checkbox_excel = new ExcelReader(Paths.checkbox_excel);
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static Logger log = Logger.getLogger("devpinoyLogger");
 

	public static void setup() throws Exception {

		if (driver == null) {

			if (Config.browser("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);
				log.info("launching chrome broswer");

			} else if (Config.browser("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("launching firefox broswer");

			} else if (Config.browser("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("launching internetexplore broswer");
			}

			driver.manage().window().maximize();
			driver.get(Config.browser("url").toString());
			log.info("Open URL from config file");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

	}

	public WebElement locator(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public List<WebElement> locators(String commanxpath) {
		return driver.findElements(By.xpath(commanxpath));
	}

	public void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();

	}

	public void clickJS(String xpath) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));

	}
	
	public void clickJSWebEle(WebElement value) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", value);
		
		}

	public void clear(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();
	}

	static List<WebElement> ite;

	public List<WebElement> iteratorClick(String commanxpath) {
		ite = driver.findElements(By.xpath(commanxpath));
		
        try {
		Iterator<WebElement> cli = ite.iterator();
		while (cli.hasNext()) {
			cli.next().click();
		}
        } catch (Exception e) {

		}

		return ite;
	}

	static List<WebElement> locats;

	public List<WebElement> iteratorGetText(String commanxpath) {
		locats = driver.findElements(By.xpath(commanxpath));
		
		try {
			Iterator<WebElement> cli2 = locats.iterator();
			while (cli2.hasNext()) {
				String ssm = cli2.next().getText();
				System.out.println(ssm);
			}
		} catch (Exception e) {

		}
		return locats;

	}

	
	static WebElement dropdown;
	public void selectVT(String xpath, String value) {
			dropdown = driver.findElement(By.xpath((xpath)));
		
		Select select1 = new Select(dropdown);
		select1.selectByVisibleText(value);

	}
	
	
	public Select select(String xpath) {
		Select select1 = new Select(driver.findElement(By.xpath((xpath))));

		return select1;
	}


	public void waitVofE(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	static WebElement moveto;
	public void moveToElement(String xpath) {

		moveto = driver.findElement(By.xpath(xpath));
	
		Actions actions = new Actions(driver);
		Action action = actions.moveToElement(moveto).build();
		action.perform();

	}

	static WebElement doubluclick;
	public void doubleClick(String xpath) {

		doubluclick = driver.findElement(By.xpath(xpath));
		
		Actions actions = new Actions(driver);
		Action action = actions.doubleClick(doubluclick).build();
		action.perform();

	}

	static WebElement draganddrop;
	static WebElement elem;
	public void actionDragAndDrop(String xpath, String ele) {

		draganddrop = driver.findElement(By.xpath(xpath));
		elem = driver.findElement(By.xpath(ele));
		
		Actions actions = new Actions(driver);
		Action action = actions.dragAndDrop(draganddrop, elem).build();
		action.perform();

	}

	
	public static void quitBrowser(){
		
		driver.quit();
		
	}

}
