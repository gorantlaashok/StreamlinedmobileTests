package pages;

import org.openqa.selenium.By;

public class ShippingOptionsAndPaymentPage {

	public static final By email = By.xpath("//input[@type='email']");
	public static final By ShippingMethodSelected = By.xpath("//*[@class='pure-u-1 checkout__gradient-container']");
	public static final By TrackableCheckProtected = By.xpath("//*[@class='pure-u-1 checkout__gradient-container']//strong[text()='Trackable CheckProtect®']");
	
	public static final By QuickShipAM = By.xpath("//*[@class='pure-u-1 checkout__gradient-container']//strong[text()='Quik-Ship® 1 Day AM']");
	public static final By QuickShipPM = By.xpath("//*[@class='pure-u-1 checkout__gradient-container']//strong[text()='Quik-Ship® 1 Day PM']");
	public static final By QuickShip = By.xpath("//*[@class='pure-u-1 checkout__gradient-container']//strong[text()='Quik-Ship® 2 Day']");
	
}
