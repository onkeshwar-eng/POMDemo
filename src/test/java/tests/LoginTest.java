package tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@DataProvider (name = "LoginData")
	public Object[][] getLoginData() throws IOException{
		String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i=1; i<rowCount; i++) {
			data[i-1][0] = ExcelUtils.getCellData(i, 0); //username
			data[i-1][1] = ExcelUtils.getCellData(i, 1); //password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	@Test (dataProvider = "LoginData")
	public void testValidLogin(String username, String password) throws InterruptedException {
		Log.info("Starting Logging Test...");
		test = ExtentReportManager.createTest("Login Test");
		
		test.info("Navigating to facebook url");
		LoginPage loginpage = new LoginPage(driver);
		
		Log.info("Adding Credentials");
		test.info("Adding Credentials");
		
//		loginpage.enterEmail("onkeshwarnath@gmail.com");
//		loginpage.enterPassword("Archana@151090");
		
		loginpage.enterEmail(username);
		loginpage.enterPassword(password);
		
		test.info("Clicking on Login Button");
		loginpage.clickLogin();
		Thread.sleep(Duration.ofSeconds(5));
		
		System.out.println("Title of the page is :"+ driver.getTitle());
		Log.info("Verifying Page Title");
		test.info("Verifying Page Title");
		Assert.assertEquals(driver.getTitle(), "Facebook");
		
		test.pass("Login Successful");
	}
	
//	@Test
//	public void testLoginWithInvalidCredentials() throws InterruptedException {
//		Log.info("Starting Logging Test...");
//		test = ExtentReportManager.createTest("Login Test with invalid credentials");
//		
//		test.info("Navigating to facebook url");
//		LoginPage loginpage = new LoginPage(driver);
//		
//		Log.info("Adding Credentials");
//		test.info("Adding Credentials");
//		
//		loginpage.enterEmail("onkeshwarnath123@gmail.com");
//		loginpage.enterPassword("Archana@151090");
//		
//		test.info("Clicking on Login Button");
//		loginpage.clickLogin();
//		Thread.sleep(Duration.ofSeconds(5));
//		
//		System.out.println("Title of the page is :"+ driver.getTitle());
//		Log.info("Verifying Page Title");
//		test.info("Verifying Page Title");
//		Assert.assertEquals(driver.getTitle(), "Facebook");
//		
//		test.pass("Login Successful");
//	}
}
