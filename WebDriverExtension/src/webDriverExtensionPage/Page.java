package webDriverExtensionPage;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;

public class Page {

	private static WebDriver driver;
		
     private static int TimeoutForElementPresence = 30;
     
	
	/* Methods are listed by alphabetical order*/
	public static void click(By _element)
	{		
		getWebDriver().findElement(_element).click();		
	}
	
	public static Boolean isAbscent(By element)
	{
		Boolean status = false;
		turnOffImplicitWaits();
		
		try
		{
			status = !getWebDriver().findElement(element).isDisplayed();			
		}catch(Exception e)
		{
			status = true;// we get an exception when element is not found
		}
		
		turnOnImplicitWaits();
		return status;		
	}
	
	public static Boolean isPresent(By element)
	{		
		return getWebDriver().findElement(element).isDisplayed();						
	}
	
	public static void enterText(By _element, String _text)
	{
		getWebDriver().findElement(_element).clear();
		getWebDriver().findElement(_element).sendKeys(_text);		
	}
		
	public static String getAttributeText(By element, String attribute)
	{
		try
		{
			return getWebDriver().findElement(element).getAttribute(attribute);
		}catch(Exception e)
		{
			return "";
		}
	}
	
	private static void getNewDriver()
	{			
		System.setProperty("webdriver.chrome.driver", "dependencies\\chromedriver.exe");
		driver = new ChromeDriver();
		turnOnImplicitWaits();		
	}
	
	public static String getText(By element) {		
		return getWebDriver().findElement(element).getAttribute("value");
	}
	
	public static void goToURL(String _url)
	{
		getWebDriver().get(_url);
	}
		
	public static WebDriver getWebDriver()
	{
		if(driver==null)		
			getNewDriver();
		return driver;
	}
	
	public static void pressKey(By element, Keys key)
	{
		getWebDriver().findElement(element).sendKeys(key);		
	}
	
	public static void setFocus(By element) 
	{
		WebElement e = getWebDriver().findElement(element);
		
		if("input".equals(e.getTagName()))		
		   e.sendKeys("");		 
		else
		   new Actions(driver).moveToElement(e).perform();		
	}
	
	public static void setWindowSize(int width, int height) {
		
		getWebDriver().manage().window().setSize(new Dimension(width, height));
	}	
	
	public static void takeScreenShot(String fileName )
	{
		
		File scrFile = ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);
		try {
			java.util.Date myDate = new Date();             
            SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
			FileUtils.copyFile(scrFile, new File( "screenShots\\" + fileName + formatter.format(myDate) + ".png"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	private static void turnOffImplicitWaits() {
		getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	private static void turnOnImplicitWaits() {
		getWebDriver().manage().timeouts().implicitlyWait(TimeoutForElementPresence, TimeUnit.SECONDS);
	}		
	
	public static Boolean isElementDisabled(By element)
	{
		String value = getWebDriver().findElement(element).getAttribute("disabled"); 
		return value.equals("true");
	}
	
	public static void quitWebDriver()
	{
		getWebDriver().quit();
		driver=null;
	}	
			
}
