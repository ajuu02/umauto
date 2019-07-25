package utils.excelutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitMethods {
		
	
	/**
	* Wait for document ready state (loaded page)
	* @param driver - current driver
	* @param MAX_WAITING_TIME - max. waiting time in seconds, preferably constant
	*/		
		public static void waitForPageToLoad(WebDriver driver, int MAX_WAITING_TIME) {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete") ||("return jQuery.active").equals("0");
				}
			};
			
			WebDriverWait wait = new WebDriverWait(driver, MAX_WAITING_TIME);
			wait.withMessage("TimeoutException: The page was not fully loaded after timeout "+MAX_WAITING_TIME+" seconds.")
			.until(pageLoadCondition);
		}
	
	
	/**
	* Waits until element is clickable (visible and enabled)
	* This methods is recommended for interactable elements (buttons, links, input fields...)
	*/
		 public static void waitUntilElementIsClickable(WebDriver driver, WebElement element, long timeOut){
				new WebDriverWait(driver,timeOut).withMessage("TimeoutException: Element was not clickable after timeout "+timeOut+" seconds.")
	            .until(ExpectedConditions.elementToBeClickable(element));
		 }
		 
	/**
	* Waits until element is displayed
	* General waiting method, suitable for all elements visible on page
	*/
	public static void waitUntilElementIsDisplayed(WebDriver driver, WebElement element, long timeOut){
		 new WebDriverWait(driver,timeOut).withMessage("TimeoutException: Element was not displayed after timeout "+timeOut+" seconds.")
		 .until(ExpectedConditions.visibilityOf(element));
	}  
	
	/**
	* Waits until element disappears
	* General waiting method, suitable for all elements visible on page
	*/
	public static void waitUntilElementDisappears(WebDriver driver, WebElement element, long timeOut){
		 new WebDriverWait(driver,timeOut).withMessage("TimeoutException: Element was not displayed after timeout "+timeOut+" seconds.")
		 .until(ExpectedConditions.invisibilityOf(element));
	}

}
