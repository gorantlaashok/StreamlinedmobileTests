package exampleTests;


import org.testng.annotations.Test;

import examplePages.GoogleHomePage;
import smTests.BaseTest;
import utility.CheckUtil;
import webDriverExtensionPage.Page;


public class SampleGoogleTest extends BaseTest {

	@Test
	public void SampleSearchTest()
	{
		Page.goToURL("http://www.google.com");
		Page.enterText(GoogleHomePage.SearchInputBox, "Hello World");
		Page.click(GoogleHomePage.SearchButton);
		System.out.println("This is SampleSearchTest");
		CheckUtil.AddCheck("SampleSTPass", "Some Error MEssage", false, true);
		CheckUtil.AddCheck("SampleSTFail", "Some Error MEssage", false, true);
	}
	
	@Test
	public void TestOne()
	{
		System.out.println("This is Test One");
		CheckUtil.AddCheck("TestOneP", "Some Error MEssage", false, true);
		CheckUtil.AddCheck("TestOneF", "Some Error MEssage", false, true);
	}
	
	@Test
	public void TestTwo()
	{
		System.out.println("This is Test Two");
		CheckUtil.AddCheck("TestTwoP", "Some Error MEssage", false, true);
		CheckUtil.AddCheck("TestTwoF", "Some Error MEssage", false, true);
	}
		
}
