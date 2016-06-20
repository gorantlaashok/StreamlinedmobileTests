package pages;

import org.openqa.selenium.By;

public class LoginPage {
	
	public static final By RoutingNumberLabel = By.xpath("(//*[@class='home__label'])[1]");
	
	public static final By RoutingInputBox = By.xpath("(//input[@name='TR'])");
	public static final By AccountInputBox = By.xpath("(//input[@name='ACCT'])");
	public static final By ContinueButton = By.xpath("//a[text()='CONTINUE']");
	
	public static final By ProductImageCarousel = By.xpath("//img[@src='/assets/img/promo-text-2.png']");
	
	public static final By ErrorMessage = By.xpath("//div[@class='error__message']");
	
	public static final By RN_HelpPopUp = By.xpath("//img[@src='/assets/img/check-personal-routing.svg']");
	
	public static final By RN_Help_PersonalCheckLink = By.xpath("(//div[text()='Personal Check'])[1]");
	public static final By RN_Help_BusinessCheckLink = By.xpath("(//div[text()='Business Check'])[1]");
	public static final By RN_Help_CreditUnionCheckLink = By.xpath("(//div[text()='Credit Unions'])[1]");
	
	public static final By RN_Help_PersonalCheckImage = By.xpath("//img[@src='/assets/img/check-personal-routing.svg']");
	public static final By RN_Help_BusinessCheckImage = By.xpath("//img[@src='/assets/img/check-business-routing.svg']");
	public static final By RN_Help_CreditUnionCheckImage = By.xpath("//img[@src='/assets/img/check-cu-routing.svg']");
	
	public static final By RoutingHelpIcon = By.xpath("(//*[@class='noselect home__info'])[1]");
	public static final By AccountNumberHelpIcon = By.xpath("(//*[@class='noselect home__info'])[2]");
	
	public static By getErrorMessage(String errorMessage)
	{
		return By.xpath("//div[@class='error__message' and text()='" + errorMessage +"']");
	}
	
}
