package com.um;

import org.testng.annotations.Test;

import com.applitools.eyes.ProxySettings;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
//import com.utilities.ExcelUtils;

import utils.excelutils.ExcelUtils;
import utils.excelutils.WaitMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class HilfeUndServicesTC3 {
	static WebDriver driver;
	static Eyes eyes;
	static String testCaseName;
	static String testUrl;
	static String browser;
	static int width;
	static int height;
	WebDriverWait wait;
	
	@BeforeTest
	  public void beforeTest() {		
		// Get Data sheet
		ExcelUtils.setExcelFileSheet("TC3");	
	    testCaseName = ExcelUtils.getCellData(1, 0);;
		testUrl= ExcelUtils.getCellData(1, 1);
		browser = ExcelUtils.getCellData(1, 2);
		String viewport = ExcelUtils.getCellData(1, 3);
		width = Integer.parseInt(viewport.split(",")[0]);
		height = Integer.parseInt(viewport.split(",")[1]);
		
		System.out.println(testCaseName+"-"+testUrl+"-"+browser+"-"+viewport);
			
		// Get Driver
		if(browser.equals("chrome")) {
			System.out.println(System.getProperty("user.dir")+ "/driver/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/driver/IEDriverServer.exe");
					
			DesiredCapabilities capsIE = new DesiredCapabilities();
		    capsIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		    capsIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		    capsIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		    driver = new InternetExplorerDriver(capsIE);
		}
		else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver.exe");
		    driver = new FirefoxDriver();	
		}else {
			System.out.println("wrong browser selection...");
		}
		
		this.driver.manage().deleteAllCookies();
		
		
		// Get Eye
		eyes = new Eyes();
		//eyes.setProxy(new ProxySettings("http://proxy:80"));
		eyes.setApiKey("mqGBBE103hV6vtjIZ7Bta6PiZIc8KEde1N9ywMdaTXliU110");
		eyes.setLogHandler(new StdoutLogHandler(true));
	  }
	
  @Test
  public void f() {
	  try {
		    // set Eye
		   driver.manage().window().setSize(new Dimension(800, 700));
		    eyes.setMatchTimeout(0);
		  	eyes.open(driver,"DCOMM",testCaseName, new RectangleSize(width, height));
			eyes.setSendDom(true);
			eyes.setStitchMode(StitchMode.CSS);
			eyes.setForceFullPageScreenshot(true);	
			driver.manage().window().setSize(new Dimension(800, 700));
			driver.get(testUrl);
			
			WebElement cookiesButton = driver.findElement(By.xpath("//button[@class='gdpr_accept_all']"));
			WaitMethods.waitUntilElementIsClickable(driver, cookiesButton, 15);
			cookiesButton.click();
			WaitMethods.waitUntilElementDisappears(driver, cookiesButton, 15);
			WaitMethods.waitForPageToLoad(driver, 10);
			
			eyes.checkWindow(testCaseName);
			eyes.close();
			
		}catch (Exception e) {
			System.out.println(e);
			eyes.abortIfNotClosed();
		} 
  }
  @AfterTest
  public void afterTest() {
	  
			driver.quit();
			eyes.abortIfNotClosed();
			System.exit(0);
		
  }
}
