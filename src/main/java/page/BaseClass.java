package page;

import org.openqa.selenium.WebDriver;

public class BaseClass {
	public static WebDriver driver;
	
	public void setDriver(WebDriver driver) {
		BaseClass.driver = driver;
	}
	
}
