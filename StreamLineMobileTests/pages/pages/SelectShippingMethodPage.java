package pages;

import org.openqa.selenium.By;

public class SelectShippingMethodPage {

	public static final By Done = By.xpath("//a[text()='Done']");
	
	public static By GetRadioOption(String heading)
	{
		return By.xpath("//*[text()='" + heading + "']/../following-sibling::div//input");
	}
}
