package smTests;
//import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import utility.CheckUtil;
import webDriverExtensionPage.Page;

public class BaseTest {

	@BeforeSuite
	public void BeforeSuite()
	{
		//PropertyConfigurator.configure("log4j.properties");	
		CheckUtil.initializeChecks();
		
		// clean up logs and screenShots folders
		try {
			FileUtils.cleanDirectory(new File("logs"));
			FileUtils.cleanDirectory(new File("screenShots"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}	
	
	@BeforeMethod
	public void BeforeMethod()
	{		
		// reset soft checks for the new test 
		CheckUtil.ResetChecks();		
	}
	
	@AfterMethod
	public void AfterMethod(ITestResult result)
	{		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			Page.takeScreenShot(result.getName());
			System.out.println("******** The Test has Failed : " + result.getName() + "********");
		}
		else
		{
			System.out.println("******** The Test has Passed : "+ result.getName() + "********");
		}
				
		// close browser, every test must use a new browser.
		Page.quitWebDriver();
		
		// print soft checks if any
		CheckUtil.PrintChecks(false);
		CheckUtil.WriteToFile(false, result);
		
		System.out.println("******** End of " + result.getName() + "********");
		
	}	
}
