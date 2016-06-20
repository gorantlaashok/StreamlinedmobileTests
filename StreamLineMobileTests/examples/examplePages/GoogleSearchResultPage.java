package examplePages;

import org.openqa.selenium.By;

public class GoogleSearchResultPage {

	public static By GetResult(int _position)
	{
		return By.xpath("(//h3)[position()=" + _position + "]");
	}
	
}
