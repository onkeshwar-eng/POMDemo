package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@Test
	public void testValidLogin() {
		Log.info("Starting Logging Test...");
		LoginPage loginpage = new LoginPage(driver);
		Log.info("Adding Credentials");
		loginpage.enterEmail("onkeshwarnath@gmail.com");
		loginpage.enterPassword("Archana@151090");
		loginpage.clickLogin();
		
		System.out.println("Title of the page is :"+ driver.getTitle());
		Log.info("Verifying Page Title");
		Assert.assertEquals(driver.getTitle(), "Facebook");
	}
}
