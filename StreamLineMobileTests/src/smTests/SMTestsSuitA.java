package smTests;

import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.OrderThankYouPage;
import pages.SelectShippingMethodPage;
import pages.ShippingOptionsAndPaymentPage;
import pages.TopLeftMenuPage;
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
	 * Test (114737) - SCR1-004-Verify RN is mandatory (seems that 113495 is duplicate of this)
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
	public void RN_MandatoryTest_114737()
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
	 * Test (114738) - SCR1-005-Verify RN only allows numeric values (seems duplicate of 113496)
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
	public void RN_OnlyNumericTest_114738()
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
	 * Test (114739) - SCR1-006-Verify RN allows up to 9 digits (seems duplicate of 113497)
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
	public void RN_MaxDigitsTest_114739()
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
		
	/*******************************************************************************************
	 * Test (114709) - 1-US4173-Verify Trademark symbol on Quikship and CheckProtect_Shipping Screen
	 * 
	 * Test Description
	 * Verify that the Shipping Methods in the shipping screen now have the trademarks next to them:
	 * Quik-Ship® 1 Day AM
	 * Quik-Ship® 1 Day PM
	 * Quik-Ship®
	 * Trackable CheckProtect®
	 * 
	 * 	
	 *******************************************************************************************/	
	@Test
	public void QuickShippingTradeMarksTest_114709()
	{
		// get login credentails
		List<List<String>> crendtials = getCredentailsData();
		String rn = "";
		String an ="";
		String email = "";
		
		for(List<String> list : crendtials)
		{
			String testID = list.get(0);
			if ( testID.equals("114709"))
			{
				rn = list.get(1);
				an = list.get(2);
				email = list.get(3);
			}
		}
		
		// login
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		
		Page.enterText(LoginPage.RoutingInputBox, rn);
		Page.enterText(LoginPage.AccountInputBox, an);
		
		Page.click(LoginPage.ContinueButton);
		
		Page.enterText(ShippingOptionsAndPaymentPage.email, email);
		
		// verify quick shop AM trade Mark
		Page.click(ShippingOptionsAndPaymentPage.ShippingMethodSelected);				
		Page.click2(SelectShippingMethodPage.GetRadioOption("Quik-Ship® 1 Day AM"));		
		Page.click(SelectShippingMethodPage.Done);		
		CheckUtil.AddCheck("QuickShipAM", "Unable to verify Quick Ship AM TradeMark", Page.isPresent(ShippingOptionsAndPaymentPage.QuickShipAM));
				
		// verify quick ship PM trade Mark
		Page.click(ShippingOptionsAndPaymentPage.ShippingMethodSelected);		
		Page.click2(SelectShippingMethodPage.GetRadioOption("Quik-Ship® 1 Day PM"));		
		Page.click(SelectShippingMethodPage.Done);		
		CheckUtil.AddCheck("QuickShipPM", "Unable to verify Quick Ship PM TradeMark", Page.isPresent(ShippingOptionsAndPaymentPage.QuickShipPM));
					
		// verify quick ship trade mark
		Page.click(ShippingOptionsAndPaymentPage.ShippingMethodSelected);		
		Page.click2(SelectShippingMethodPage.GetRadioOption("Quik-Ship® 2 Day"));		
		Page.click(SelectShippingMethodPage.Done);		
		CheckUtil.AddCheck("QuickShip", "Unable to verify Quick Ship TradeMark", Page.isPresent(ShippingOptionsAndPaymentPage.QuickShip));
		
		// verify quick ship check protected
		Page.click(ShippingOptionsAndPaymentPage.ShippingMethodSelected);		
		Page.click2(SelectShippingMethodPage.GetRadioOption("Trackable CheckProtect®"));		
		Page.click(SelectShippingMethodPage.Done);		
		CheckUtil.AddCheck("CheckProtected", "Unable to verify Trackable Check Protected TradeMark", Page.isPresent(ShippingOptionsAndPaymentPage.TrackableCheckProtected));
		
		Assert.assertTrue(CheckUtil.GetStatus(), "At least one of the Checks has failed");
						
	}
	
	
	
	
	/*******************************************************************************************
	 * Test (114793) - SCR4-008-Verify Address Email (pre-populated if on the order)
	 * 
	 * Requirements:
	 *	BR-09 If the FI has an email address stored for the customer/account holder, the email address should prepopulate
	 *	any email address fields within the application.
	 *	
	 *	Test Description:
	 *		Verify that Email address is prepopulated for the customer based on last order (assumption : user has entered
	 *	an email address on the order)
	 *	Test Validations:
	 *
	 *	Pre-Conditions:
	 *	 1. User is logged into Mobile Banking
	 *	 2. User has already clicked on the Order/Review Check out screen
	 * 	
	 *******************************************************************************************/	
	@Test
	public void EmailTest_114793()
	{
		// get login credentails
		List<List<String>> crendtials = getCredentailsData();
		String rn = "";
		String an ="";
		String email = "";
		String email2 = "";
		
		for(List<String> list : crendtials)
		{
			String testID = list.get(0);
			if ( testID.equals("114793"))
			{
				rn = list.get(1);
				an = list.get(2);
				email = list.get(3);
				email2 = list.get(4);
			}
		}
				
		// login
		Page.goToURL(siteURL);
		Page.setWindowSize(650,900);
		
		Page.enterText(LoginPage.RoutingInputBox, rn);
		Page.enterText(LoginPage.AccountInputBox, an);
		
		Page.click(LoginPage.ContinueButton);		
		
		// verify email is pre-populated
		String currentEmail = Page.getText(ShippingOptionsAndPaymentPage.email);		
		CheckUtil.AddCheck("EmailPrePopulated", "Expected Email to be filled, but is null/empty", !currentEmail.isEmpty());
		
		// update email to something different than what it is currently
		String UpdatedEmail = "";
		if (currentEmail.equals(email))
			UpdatedEmail = email2;			
		else
			UpdatedEmail = email;
		
		Page.enterText(ShippingOptionsAndPaymentPage.email, UpdatedEmail);
		
		// submit order
		Page.click(ShippingOptionsAndPaymentPage.SubmitOrder);
		
		// logout
		Page.click(OrderThankYouPage.LogOut);
		
		// login
		Page.enterText(LoginPage.RoutingInputBox, rn);
		Page.enterText(LoginPage.AccountInputBox, an);
		
		Page.click(LoginPage.ContinueButton);	
		
		// verify email is now the last updated email
		currentEmail = Page.getText(ShippingOptionsAndPaymentPage.email);		
		CheckUtil.AddCheck("LastEmailListed", "Expected " + UpdatedEmail + " Actual Email " + currentEmail, currentEmail.equals(UpdatedEmail));
		
		Assert.assertTrue(CheckUtil.GetStatus(), "At least one of the Checks has failed");
		
		// logout
		Page.click(ShippingOptionsAndPaymentPage.TopLeftMenu);
		Page.click(TopLeftMenuPage.LogOut);
		
	}
	
	
	
	
	/*******************************************************************************************
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 	
	 *******************************************************************************************/	
	
	
	
	private List<List<String>> getCredentailsData()
	{
		String workingDir = System.getProperty("user.dir");		   
		List<List<String>> SheetRows = Excel.readExcelFile(workingDir + "/TestData/LoginCredentails.xlsx",0);
		
		return SheetRows;							
	}
	
	
	
}
