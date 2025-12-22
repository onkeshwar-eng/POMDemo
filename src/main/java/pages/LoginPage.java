package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Log;

public class LoginPage {
	
    private WebDriver driver;
	
    public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

    private By emailTxtBox = By.id("email");
    private By passTxtBox = By.id("pass");
    private By loginBtn = By.name("login");
    
	
	public void enterEmail(String email) {
		driver.findElement(emailTxtBox).clear();
		driver.findElement(emailTxtBox).sendKeys(email);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passTxtBox).clear();
		driver.findElement(passTxtBox).sendKeys(password);
	}
	
	public void clickLogin() {
		Log.info("Clicking Login Button..");
		driver.findElement(loginBtn).click();
	}
	
	
}
