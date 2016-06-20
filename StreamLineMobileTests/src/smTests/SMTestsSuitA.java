package smTests;

import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.SelectShippingMethodPage;
import pages.ShippingOptionsAndPaymentPage;
import utilitiesPackage.Excel;
import utility.CheckUtil;
import webDriverExtensionPage.Page;

public class SMTestsSuitA extends BaseTest {

	String siteURL = "https://uat.ordermychecks.com/login_a.jsp";
	
	/*******************************************************************************************
	 * Test (113494) - SCR1-003-Verfiy Product Image Carousel
	 * 
	 * Test Description:
	 * This test is to verify the product image carousel (Ordermychecks.com by Harland Clarke)
	 * 
	 * Test Validation:
	 *	1. Carousel is a static image
	 *	2. Image verification
	 *******************************************************************************************/	
	@Test
	public void productImageTest_113494()
	{
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		Assert.assertTrue(Page.isPresent(LoginPage.ProductImageCarousel), "Failed to Verify Carousel Image");				
	}
		
	
	/*******************************************************************************************
	 * Test (113495) - SCR1-004-Verify RN is mandatory
	 * 
	 * Test Description:
	 * This test is to verify the RN field is mandatory (Ordermychecks.com by Harland Clarke)
	 * 
	 * Test Validation:
	 * 1.Validate mandatory field
	 * 2. Validate user enters the checking routing account routing number
	 * 3. Validate RN field with on-focus help
	 * 4. Validate image gets displayed when user starts typing into field
	 * 5. Validate image is suppressed if user taps anywhere else on the screen
	 * 6. Validate help icon is visible
	 * 7. Validate help image personal check image by default. links to business
	 * and 'other' will display corresponding help image
	 *******************************************************************************************/	
	@Test
	public void RN_MandatoryTest_113495()
	{
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		
		 // 1.Validate mandatory field
				
		CheckUtil.AddCheck( "RN1_DefaultText", 
							"Routing Number Default Text, Expected: 'Enter 9 digits ' , Actual: " + Page.getAttributeText(LoginPage.RoutingInputBox, "placeholder"), 
							Page.getAttributeText(LoginPage.RoutingInputBox, "placeholder").equals("Enter 9 digits"));			
			
		CheckUtil.AddCheck( "RN1_ContinueDisabled", 
							"Continue button must be disabled", 
							Page.isElementDisabled(LoginPage.ContinueButton));
		
		 // 2. Validate user enters the checking routing account routing number
		Page.enterText(LoginPage.RoutingInputBox, "123456789");
				
		CheckUtil.AddCheck( "RN2_ValidNumber",
							"Expecting No Error Messages, Actual: Error Message is displayed",
							Page.isAbscent(LoginPage.ErrorMessage));		
		
		 // 3. Validate RN field with on-focus help
		Page.pressKey(LoginPage.RoutingInputBox, Keys.TAB);
		//Page.click(LoginPage.ProductImageCarousel);// move away and then set focus
		Page.setFocus(LoginPage.RoutingInputBox);
		
		CheckUtil.AddCheck( "RN3_OnfocusHelp",
							"Expecting Help popup",
							Page.isPresent(LoginPage.RN_HelpPopUp));	
		
		 // 4. Validate image gets displayed when user starts typing into field
		Page.enterText(LoginPage.RoutingInputBox, "123");
		
		CheckUtil.AddCheck( "RN4_ValidationMessage",
							"Expecting Help popup",
							Page.isPresent(LoginPage.RN_HelpPopUp));
		
		 // 5. Validate image is suppressed if user taps anywhere else on the screen
		Page.click(LoginPage.RoutingNumberLabel);
		
		CheckUtil.AddCheck( "RN5_ValidationMessage",
							"Expected Popup Help image to go away",
							Page.isAbscent(LoginPage.RN_HelpPopUp));
						
		 // 6. Validate help icon is visible
		CheckUtil.AddCheck(	"RN6_HelpIcon",
							"Expecting Help Icon is Displayed inside RoutingNumber text box",
							Page.isPresent(LoginPage.RoutingHelpIcon));
		
		 // 7. Validate help image personal check image by default. links to business
		 // and 'other' will display corresponding help image
		
		Page.click(LoginPage.RoutingInputBox);
		
		CheckUtil.AddCheck(	"RN7_PersonalCheckImage",
							"Expecting: Personal Check image is Displayed by Default",
							Page.isPresent(LoginPage.RN_Help_PersonalCheckImage));
		
		Page.click(LoginPage.RN_Help_BusinessCheckLink);
		
		CheckUtil.AddCheck(	"RN7_BusinessCheckImage",
							"Expecting: Business Check image is Displayed",
							Page.isPresent(LoginPage.RN_Help_BusinessCheckImage));
		
		Page.click(LoginPage.RN_Help_CreditUnionCheckLink);
		
		CheckUtil.AddCheck(	"RN7_CreditUnionCheckImage",
							"Expecting: Credit Union Check image is Displayed",
							Page.isPresent(LoginPage.RN_Help_CreditUnionCheckImage));
		
		Assert.assertTrue(CheckUtil.GetStatus(), "At least one of the Checks has failed");
		
	}
	
	
	/*******************************************************************************************
	 * Test (113496) - SCR1-005-Verify RN only allows numeric values
	 * 
	 * Test Description:
	 * This test is to verify the RN field only allows numeric values (Ordermychecks.com by Harland Clarke)
	 * 
	 * Test Validation:
	 * 1.Validate RN allows only numeric values
	 * 
	 * Pre-Conditions:
	 * N/A
	 * 
	 * Test Data:
	 * N/A
	 * 	
	 *******************************************************************************************/	
	@Test
	public void RN_OnlyNumericTest_113496()
	{
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		
		Page.enterText(LoginPage.RoutingInputBox, "a12345678");
		Page.enterText(LoginPage.AccountInputBox, "1234");
		
		CheckUtil.AddCheck( "RN1_ContinueDisabled", 
							"Expecting Continue button is still disabled, as Routing Number is Alphanemeric", 
							Page.isElementDisabled(LoginPage.ContinueButton));
		
		Assert.assertTrue(CheckUtil.GetStatus(), "At least one of the Checks has failed");
	}
	
	
	/*******************************************************************************************
	 * Test (113497) - SCR1-006-Verify RN allows up to 9 digits
	 * 
	 * Test Description:
	 * This test is to verify the RN field allows upto 9 digits (Ordermychecks.com by Harland Clarke)
	 * 
	 * Test Validation:
	 * 1. Validate RN only allows upto 9 digits
	 * 
	 * Pre-Conditions:
	 * N/A
	 * 
	 * Test Data:
	 * N/A
	 * 	
	 *******************************************************************************************/	
	@Test
	public void RN_MaxDigitsTest_113497()
	{
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		
		Page.enterText(LoginPage.RoutingInputBox, "12345678910");
		String acceptedText = Page.getText(LoginPage.RoutingInputBox);
		
		CheckUtil.AddCheck("RN_Maximum 9 Digits",
							"Expecting Entered Text Length of 9 Actual " + String.valueOf(acceptedText.length()),
							acceptedText.length() == 9);
		
		Assert.assertTrue(CheckUtil.GetStatus(), "At least one of the Checks has failed");
		
				
	}
		
	
	
	
	
}
