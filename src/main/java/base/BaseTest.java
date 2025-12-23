package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
    
	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

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
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, "Login Failure");
			System.out.println("ScreenShot Captures, PATH:"+screenshotPath);
			test.fail("Test failed. screenshot attached.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		if (driver != null) {
			Log.info("Closing Browser...");
			driver.quit();
		}
	}
}
