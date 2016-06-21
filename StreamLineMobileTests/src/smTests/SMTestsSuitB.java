package smTests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utility.CheckUtil;
import webDriverExtensionPage.Page;

public class SMTestsSuitB extends BaseTest {

	String siteURL = "https://uat.ordermychecks.com/login_a.jsp";

	@Test
	public void verifyProductImageTest_114736() {

		Page.goToURL(siteURL);
		Page.setWindowSize(650, 900);
		CheckUtil.AddCheck("Ordermychecks.com by Harland Clarke)",
				"Expecting: Ordermychecks.com by Harland Clarke is Displayed",
				Page.isPresent(LoginPage.ProductImageCarousel));
		System.out.println(" Verified that product image carousel is displayed ");

	}

	@Test
	public void verifyPersonal_BussinesButtonRequiredTest_114766() {

		Page.goToURL(siteURL);
		Page.setWindowSize(650, 900);
		CheckUtil.AddCheck("Verify that Personal  Radio button selection is required or not",
				"Expecting personal  radio button is still disabled for mobile Application",
				Page.isAbscent(LoginPage.PersonalRadioButton));
		CheckUtil.AddCheck("Verify that Bussiness  Radio button selection is required or not  ",
				"Expecting Bussiness  radio button is still disabled for mobile Application",
				Page.isAbscent(LoginPage.BussinessRadioButton));
		System.out.println(
				" Verified that the selection of Personal or Business radio buttons is notrequired forStreamlined  Mobile Application:");
	}

	@Test
	public void verifyLoginTextChange_114767() {

		Page.goToURL(siteURL);
		Page.setWindowSize(650, 900);
		Assert.assertEquals("Need more checks? Use your checking account information to begin.",
				Page.getTextMessage(LoginPage.LoginTextMessage));

		System.out.println(
				" Verified that the 'Need more checks? Use your checking account information to begin ' is visible to user");

	}

	@Test
	public void loginToHappyPath_114839() {
		Page.goToURL(siteURL);
		Page.setWindowSize(650, 900);
		Page.enterText(LoginPage.RoutingInputBox, "011304478");
		Page.enterText(LoginPage.AccountInputBox, "1410002867");
		Page.click(LoginPage.ContinueButton);
		System.out.println(
				"User is Sucessfully able to Login to Stremline mobile URL with Given Routing Number and Account Number ");

	}

	@Test
	public void menuIcon() {

		Page.goToURL(siteURL);
		Page.setWindowSize(650, 900);
		Page.click(LoginPage.MenuIconAction);
		List<WebElement> menuItems = Page.clickForList(LoginPage.MenuIcon);
		/*
		 * ArrayList<String> list= new ArrayList<String>(); list.add(
		 * "Personal Products"); list.add("Business Products"); list.add(
		 * "Customer Service"); list.add("Order Status"); list.add("Español");
		 * list.add("About Harland Clarke"); list.add("Privacy & security"); for
		 * (int i = 0; i < list.size(); i++) { System.out.println(list.get(i));
		 * }
		 */
		for (int i = 0; i < menuItems.size(); i++) {
			System.out.println(menuItems.get(i).getText());
			// System.out.println(list.get(i).toString());
			// Assert.assertEquals(menuItems.get(i).getText(), list.get(i));

		}
	}
}
