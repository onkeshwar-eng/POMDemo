package tests;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@Test
	public void testValidLogin() throws InterruptedException {
		Log.info("Starting Logging Test...");
		test = ExtentReportManager.createTest("Login Test");
		
		test.info("Navigating to facebook url");
		LoginPage loginpage = new LoginPage(driver);
		
		Log.info("Adding Credentials");
		test.info("Adding Credentials");
		
		loginpage.enterEmail("onkeshwarnath@gmail.com");
		loginpage.enterPassword("Archana@151090");
		
		test.info("Clicking on Login Button");
		loginpage.clickLogin();
		Thread.sleep(Duration.ofSeconds(5));
		
		System.out.println("Title of the page is :"+ driver.getTitle());
		Log.info("Verifying Page Title");
		test.info("Verifying Page Title");
		Assert.assertEquals(driver.getTitle(), "Facebook");
		
		test.pass("Login Successful");
	}
	
	@Test
	public void testLoginWithInvalidCredentials() throws InterruptedException {
		Log.info("Starting Logging Test...");
		test = ExtentReportManager.createTest("Login Test with invalid credentials");
		
		test.info("Navigating to facebook url");
		LoginPage loginpage = new LoginPage(driver);
		
		Log.info("Adding Credentials");
		test.info("Adding Credentials");
		
		loginpage.enterEmail("onkeshwarnath123@gmail.com");
		loginpage.enterPassword("Archana@151090");
		
		test.info("Clicking on Login Button");
		loginpage.clickLogin();
		Thread.sleep(Duration.ofSeconds(5));
		
		System.out.println("Title of the page is :"+ driver.getTitle());
		Log.info("Verifying Page Title");
		test.info("Verifying Page Title");
		Assert.assertEquals(driver.getTitle(), "Facebook");
		
		test.pass("Login Successful");
	}
}
