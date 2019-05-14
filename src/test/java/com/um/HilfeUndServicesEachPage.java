package com.um;

import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;

public class HilfeUndServicesEachPage {
	static WebDriver driver;
	static Eyes eyes;
	static Integer ctr=1;
	public static final String DATAFILE_PATH = "./dataSource/data.xlsx";
	Properties  obj;
	
	@BeforeTest
	  public void beforeTest() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\chromedriver1.exe");
//		driver = new ChromeDriver();
		
		System.setProperty("webdriver.ie.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		eyes = new Eyes();
		eyes.setApiKey("C2qG8XE4ly6lySHaFHV0ZicyAtNakEdgD5iZk2LJ56k110");
		eyes.setLogHandler(new StdoutLogHandler(true));
		obj = new Properties();
	  }
	
  @Test
  public void f() {
	  try {
		  
		   FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\dataSource\\data.properties");
		   obj.load(objfile);

		    eyes.open(driver, "Web",obj.getProperty("TC-1"), new RectangleSize(600, 600));
//		    eyes.open(driver, "Web", obj.getProperty("TC-1"));
			driver.manage().window().maximize();
			eyes.setSendDom(false);
//			eyes.setStitchMode(StitchMode.CSS);
//			eyes.setForceFullPageScreenshot(true);
//			driver.get("https://www.unitymedia.de/privatkunden/showcase-komponenten/jan-test/aufnahmefunktion");			
			driver.get(obj.getProperty("url-1"));
//			driver.manage().window().maximize();
			driver.findElement(By.xpath("//button[contains(text(), 'Einverstanden und fortfahren')]")).click();
			
//			eyes.checkWindow("Home Page 2");
//			
//				driver.findElements(By.xpath("//ul[@class='nav-list js_main-nav-list']/li")).get(1).click();
//			
//			eyes.checkWindow("Menu");
//			
//				driver.findElements(By.xpath("//a[contains(text(), '2play START 30')]")).get(0).click();	
//			
//			eyes.checkWindow("2play START 30");
				scrollToBottom(driver,ctr);
			eyes.close();
		}catch (Exception e) {
			System.out.println(e);
		} finally {
			driver.quit();
			eyes.abortIfNotClosed();
			System.exit(0);
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
  }

}
