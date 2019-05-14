package com.um;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
/**
 * Hello world!
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.ProxySettings;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;

public class App {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\chromedriver1.exe");
		WebDriver driver = new ChromeDriver();

		
//		System.setProperty("webdriver.ie.driver", "C:\\Users\\ajay.kumar.ram.dhani\\aplitools\\um\\driver\\IEDriverServer.exe");
//		WebDriver driver = new InternetExplorerDriver();

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		Eyes eyes = new Eyes();
		eyes.setApiKey("C2qG8XE4ly6lySHaFHV0ZicyAtNakEdgD5iZk2LJ56k110");
		eyes.setLogHandler(new StdoutLogHandler(true));
		try {
//			eyes.setProxy(new ProxySettings("http://proxy:80/"));
			eyes.open(driver, "App","8May", new RectangleSize(600, 600));
			driver.manage().window().maximize();
			eyes.setSendDom(false);
			eyes.setStitchMode(StitchMode.CSS);
            eyes.setForceFullPageScreenshot(true);

			
			driver.get("https://www.unitymedia.de/privatkunden/hilfe_service/horizon-tv/aufnahmefunktion.html");
			
//			driver.manage().window().maximize();
			
			eyes.checkWindow("Home Page 1");
			
				driver.findElement(By.xpath("//button[contains(text(), 'Einverstanden und fortfahren')]")).click();
			
			eyes.checkWindow("Home Page 2");
			
				driver.findElements(By.xpath("//ul[@class='nav-list js_main-nav-list']/li")).get(1).click();
			
			eyes.checkWindow("Menu");
			
				driver.findElements(By.xpath("//a[contains(text(), '2play START 30')]")).get(0).click();	
			
			eyes.checkWindow("2play START 30");
//				scrollToBottom(driver);
			eyes.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
//			driver.quit();
			eyes.abortIfNotClosed();
			System.exit(0);
		}

	}
	
	public static void scrollToBottom(WebDriver driver,Integer ctr) throws InterruptedException {
	    JavascriptExecutor jsExec = (JavascriptExecutor)driver;

	    jsExec.executeScript("window.scrollTo(0, 0);"); //Scroll To Top

	    Long innerHeight = (Long) jsExec.executeScript("return window.innerHeight;");
	    Long scroll = innerHeight;

	    Long scrollHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;"); 

	    scrollHeight = scrollHeight + scroll;
	    do{
	    	Thread.sleep(2000);
	    	jsExec.executeScript("window.scrollTo(0, "+innerHeight+");");
	        innerHeight = innerHeight + scroll;

	    }while(scrollHeight >= innerHeight);

//	    }
	    
	}
}