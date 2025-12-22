package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Log;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		Log.info("Starting WebDriver...");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		Log.info("Navigating to facebook url...");
		driver.get("https://www.facebook.com/");
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			Log.info("Closing Browser...");
			driver.quit();
		}
	}
}
