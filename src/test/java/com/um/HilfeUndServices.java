package com.um;

import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
//import com.utilities.ExcelUtils;

import utils.excelutils.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

public class HilfeUndServices {
	static WebDriver driver;
	static Eyes eyes;
	static String testCaseName;
	static String testUrl;
	static String browser;
	static int width;
	static int height;
	
	@BeforeTest
	  public void beforeTest() {		
		// Get Data sheet
		ExcelUtils.setExcelFileSheet("TC1");
		
	    testCaseName = ExcelUtils.getCellData(1, 0);;
		testUrl= ExcelUtils.getCellData(1, 1);
		browser = ExcelUtils.getCellData(1, 2);
		String viewport = ExcelUtils.getCellData(1, 3);
		width = Integer.parseInt(viewport.split(",")[0]);
		height = Integer.parseInt(viewport.split(",")[1]);
		
		System.out.println(testCaseName+"-"+testUrl+"-"+browser+"-"+viewport);
			
		// Get Driver
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();	
		}else {
			System.out.println("wrong browser selection...");
		}
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		// Get Eye
		eyes = new Eyes();
		eyes.setApiKey("mqGBBE103hV6vtjIZ7Bta6PiZIc8KEde1N9ywMdaTXliU110");
		eyes.setLogHandler(new StdoutLogHandler(true));
	  }
	
  @Test
  public void f() {
	  try {
		    // set Eye
		    eyes.open(driver, "Web",testCaseName, new RectangleSize(width, height));
			eyes.setSendDom(false);
			eyes.setStitchMode(StitchMode.CSS);
			eyes.setForceFullPageScreenshot(true);	
			
			driver.get(testUrl);

			Thread.sleep(10000);
			eyes.checkWindow(testCaseName);

//			
//				driver.findElements(By.xpath("//ul[@class='nav-list js_main-nav-list']/li")).get(1).click();
//			
//			eyes.checkWindow("Menu");
//			
//				driver.findElements(By.xpath("//a[contains(text(), '2play START 30')]")).get(0).click();	
//			
//			eyes.checkWindow("2play START 30");
//				scrollToBottom(driver,ctr);
			eyes.close();
		}catch (Exception e) {
			System.out.println(e);
			eyes.abortIfNotClosed();
		} 
  }
 
	public static void scrollToBottom(WebDriver driver, Integer ctr) throws InterruptedException {
	    JavascriptExecutor jsExec = (JavascriptExecutor)driver;

	    jsExec.executeScript("window.scrollTo(0, 0);"); //Scroll To Top

	    Long innerHeight = (Long) jsExec.executeScript("return window.innerHeight;");
	    Long scroll = innerHeight;

	    Long scrollHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;"); 

	    scrollHeight = scrollHeight + scroll;
	    do{
	    	Thread.sleep(2000);
	    	eyes.checkWindow("HilfeUndServices_"+ctr.toString());
	    	jsExec.executeScript("window.scrollTo(0, "+innerHeight+");");
	        innerHeight = innerHeight + scroll;
	        ctr=ctr+1;

	    }while(scrollHeight >= innerHeight);

//	    }
	    
	}

  @AfterTest
  public void afterTest() {
	  
			driver.quit();
			eyes.abortIfNotClosed();
			System.exit(0);
		
  }
}
